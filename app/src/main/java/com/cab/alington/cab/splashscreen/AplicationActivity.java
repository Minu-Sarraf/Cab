package com.cab.alington.cab.splashscreen;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import java.util.HashMap;

/**
 * Created by sabin on 3/2/15.
 */
public class AplicationActivity extends Application {

    private static final String PROPERTY_ID = "UA-60329710-1";
    static AplicationActivity mInstance;
    public enum TrackerName {
        APP_TRACKER
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       MultiDex.install(this);
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

    public static synchronized AplicationActivity getInstance() {
        return mInstance;
    }
}
