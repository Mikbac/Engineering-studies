package com.notebook;

import android.content.Context;

public class Application extends android.app.Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        Application.context = getApplicationContext();
    }

    public static Context getContext() {
        return Application.context;
    }
}
