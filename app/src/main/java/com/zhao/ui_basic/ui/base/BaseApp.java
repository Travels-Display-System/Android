package com.zhao.ui_basic.ui.base;

import android.app.Application;

public class BaseApp extends Application {

    private static BaseApp mBaseApp;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public static synchronized BaseApp getmBaseApp() {
        if (mBaseApp == null) {
            mBaseApp = new BaseApp();
        }
        return mBaseApp;
    }
}
