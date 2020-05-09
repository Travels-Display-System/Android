package com.zhao.ui_basic.ui.main.webwocket;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;


import androidx.core.app.NotificationCompat;

import com.zhao.ui_basic.ui.main.MainActivity;
import com.zhao.ui_basic.ui.main.util;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class WebClient extends WebSocketClient{
        public static final String ACTION_RECEIVE_MESSAGE = "com.jinuo.mhwang.servermanager";
        public static final String KEY_RECEIVED_DATA = "data";
//    private static final int R = 0;
    private static WebClient mWebClient;
        private Context mContext;
//        private Context mContext;
        /**
         *  路径为ws+服务器地址+服务器端设置的子路径+参数（这里对应服务器端机器编号为参数）
         *  如果服务器端为https的，则前缀的ws则变为wss
         */
        private static final String mAddress = "ws://39.106.60.24:30208/socket/";
//    private static final String mAddress = "ws://localhost:30208/socket/";

    private void showLog(String msg){
            Log.d("WebClient---->", msg);
    }

    private WebClient(URI serverUri,Context context){
            super(serverUri, new Draft_6455());
            mContext = context;
            showLog("WebClient");
    }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            showLog("open->"+handshakedata.toString());
        }

        @Override
        public void onMessage(String message) {
            showLog("onMessage->"+message);
            System.out.println(message);
            String m="";
            String u="";
            message=message.replace("}","");
            message=message.replace("{","");
           String[] result=message.split(",");
            for(String r:result){
                if(r.contains("massage=")){
                    m=r.substring(9,r.length());
                }
                if(r.contains("username=")){
                    u=r.substring(10,r.length());

                }
            }

            util util = null;
            util.showNotification(mContext,"通知","亲爱的"+u+","+m,MainActivity.class);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            showLog("onClose->"+reason);
        }

        @Override
        public void onError(Exception ex) {
            showLog("onError->"+ex.toString());
        }


        public static void initWebSocket(final String username,final Context context){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mWebClient = new WebClient(new URI(mAddress+username),context);
                        System.out.println(mAddress+username);
                        try {
                            mWebClient.connectBlocking();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        /** 发送消息广播
         * @param message
         */
        private void sendMessageBroadcast(String message){
            if (!message.isEmpty()){
                Intent intent = new Intent();
                intent.setAction(ACTION_RECEIVE_MESSAGE);
                intent.putExtra(KEY_RECEIVED_DATA,message);
                showLog("发送收到的消息");
//                mContext.sendBroadcast(intent);
            }
        }

}
