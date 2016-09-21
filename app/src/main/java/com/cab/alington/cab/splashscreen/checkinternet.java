package com.cab.alington.cab.splashscreen;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.cab.alington.cab.R;

import java.io.IOException;
import java.util.Calendar;





/**
 * Created by User on 4/14/2016.
 */
public class checkinternet {


    public static boolean isWifiEnable() {
        return isWifiEnable;
    }

    public static void setIsWifiEnable(boolean isWifiEnable) {
        checkinternet.isWifiEnable = isWifiEnable;
    }

    public static boolean isMobileNetworkAvailable() {
        return isMobileNetworkAvailable;
    }

    public static void setIsMobileNetworkAvailable(boolean isMobileNetworkAvailable) {
        checkinternet.isMobileNetworkAvailable = isMobileNetworkAvailable;
    }

    private static boolean isWifiEnable = false;
    private static boolean isMobileNetworkAvailable = false;

    public static boolean isNetWorkAvailableNow(Context context) {

        boolean isNetworkAvailable = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        setIsWifiEnable(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
        setIsMobileNetworkAvailable(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected());

        if (isWifiEnable() || isMobileNetworkAvailable()) {
        /*Sometime wifi is connected but service provider never connected to internet
        so cross check one more time*/
           // if (isOnline())
                isNetworkAvailable = true;
        }

        return isNetworkAvailable;
    }

    public static boolean isOnline() {
    /*Just to check Time delay*/
        long t = Calendar.getInstance().getTimeInMillis();

        Runtime runtime = Runtime.getRuntime();
        try {
        /*Pinging to Google server*/
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            long t2 = Calendar.getInstance().getTimeInMillis();
            Log.i("NetWork check Time", (t2 - t) + "");
        }
        return false;
    }

}


