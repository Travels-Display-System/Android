package com.zhao.ui_basic.ui.main;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.zhao.ui_basic.R;

public class util {
    public static void showNotification(Context context, String title, String msg, Class<?> activity) {
        PendingIntent pendingIntent = null;
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification;
        if (activity != null) {
            Intent intent = new Intent(context, activity);
            intent.putExtra("i", 1);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                notification = new Notification.Builder(context)
                        .setContentIntent(pendingIntent)//跳转的activity
                        .setContentTitle(title).setAutoCancel(true)//标题和点击消失
                        .setContentText(msg)//文本
                        .setSmallIcon(R.mipmap.ic_launcher)//图标
                        .build();
            } else {
                notification = new Notification.Builder(context)
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title).setAutoCancel(true)
                        .setContentText(msg)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .getNotification();
            }
            manager.notify(123, notification);
        }

    }

}
