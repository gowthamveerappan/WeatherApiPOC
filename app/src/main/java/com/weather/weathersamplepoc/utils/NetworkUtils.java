package com.weather.weathersamplepoc.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    /*
    *Method to check the internet availability to the applicaiton
    * OUTPUT : boolean value true if network available
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
