package com.wings.v2ex;

import android.app.Application;

/**
 * Created by wing on 16/8/8.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
