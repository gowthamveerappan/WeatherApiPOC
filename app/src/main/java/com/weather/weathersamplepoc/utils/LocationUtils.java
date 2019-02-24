package com.weather.weathersamplepoc.utils;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;

public class LocationUtils {

    LocationManager locationManager;

    public void getLocation(Context context, LocationListener locationListener) {
        try {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }
}
