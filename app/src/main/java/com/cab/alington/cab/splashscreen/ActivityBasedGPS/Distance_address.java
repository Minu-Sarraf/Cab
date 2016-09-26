package com.cab.alington.cab.splashscreen.ActivityBasedGPS;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.cab.alington.cab.splashscreen.AplicationActivity;


import java.util.List;
import java.util.Locale;

/**
 * Created by User on 7/4/2016.
 */
public class Distance_address {
    static String address;
    public static double distFrom(double lat1, double lon1, double lat2, double lon2) {
        Location selected_location = new Location("locationA");
        selected_location.setLatitude(lat1);
        selected_location.setLongitude(lon1);
        Location near_locations = new Location("locationA");
        near_locations.setLatitude(lat2);
        near_locations.setLongitude(lon2);
        Log.e("distfrom", lat1 + "   lon" + lat2);
        double distance = selected_location.distanceTo(near_locations);
        return distance;
        /*double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);*/
    }

    public static String getaddress(final double lat2, final double lon2) {

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                Geocoder myLocation = new Geocoder(AplicationActivity.getInstance(), Locale.getDefault());
                try {
                    List<Address> myList = myLocation.getFromLocation(lat2, lon2, 1);
                    if (myList != null && myList.size() > 0) {

                        address = myList.get(0).getAddressLine(0) + ", " + myList.get(0).getLocality();
                    }
                } catch (Exception e) {

                }
            }
        });
        th.start();
        return address;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
