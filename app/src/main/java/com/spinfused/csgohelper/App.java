package com.spinfused.csgohelper;

import android.app.Application;
import android.content.Context;

public class App extends Application { //Tool to give us context anywhere in the application

    private static App instance;

    public static Context getContext(){
         return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
