package com.cab.alington.cab.splashscreen.ActivityBasedGPS;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import com.cab.alington.cab.splashscreen.UICallback;
import com.google.android.gms.location.FusedLocationProviderApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 6/29/2016.
 */
public class LocationUpdates extends IntentService implements UICallback {
    private String TAG = this.getClass().getSimpleName();
    Location location;
    String address;
    String formatted;
    SharedPreferences abc;
    SharedPreferences.Editor editor;

    public LocationUpdates() {
        super("Fused Location");
    }

    public LocationUpdates(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(TAG, "onHandleIntent");
        double lat2 = 0, lon2 = 0;
        location = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);
        if (location != null) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = new Date(location.getTime());
            formatted = format.format(date);
            lat2 = location.getLatitude();
            lon2 = location.getLongitude();

            abc = getSharedPreferences("location", MODE_PRIVATE);
            editor = abc.edit();
            editor.putString("lat", String.valueOf(lat2));
            editor.putString("lon", String.valueOf(lon2));
            editor.putFloat("speed", (location.getSpeed()));
            editor.putString("accuracy", String.valueOf(location.getAccuracy()));
            editor.putString("time", String.valueOf(formatted));
            editor.putString("source", location.getProvider());
            editor.commit();
            double lat1 = Double.parseDouble(abc.getString("lat_saved2db", "0.0"));
            double lon1 = Double.parseDouble(abc.getString("lon_saved2db", "0.0"));

            double distance = Distance_address.distFrom(lat1, lon1, lat2, lon2);
            Log.e("distance", lat1 + " " + lon1 + lat2 + "sec  \n" + lon2 + "   \n " + String.valueOf(distance));
            if (lat1 != lat2) {
                showToast("Speed: " + abc.getFloat("speeed", 0) + "distance:" + distance, lat2, lon2, location.getProvider(), abc.getString("latestactivity", "holding"), distance);
            }
        }
    }

    public void showToast(String message, final double lat2, final double lon2, final String provider, final String activity, final double distance) {
        final String msg = message;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //     Toast.makeText(getApplicationContext(), msg + activity, Toast.LENGTH_LONG).show();
                if ( distance>10) {

                    try {
                        Log.e("speed", "locationupdate" + activity);
                        editor = abc.edit();
                        editor.putString("lat_saved2db", String.valueOf(lat2));
                        editor.putString("lon_saved2db", String.valueOf(lon2));
                        editor.commit();
                     /*   HttpResponse.listener("gps", Constant.gpspollingAPI,
                                UsefulInfo.addpostdata("none").add("latitude", String.valueOf(lat2)).add("longitude", String.valueOf(lon2)).add("provider", provider).add("activity", activity).add("speed", String.valueOf(distance)).build(),
                                LocationUpdates.this);*/

                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    @Override
    public void update(String uri, ArrayList<String> urltype) {
    }

    @Override
    public void refresh_interface(String b, int a) {
    }


}
