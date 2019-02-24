package com.weather.weathersamplepoc.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ApiRequestManager {

    private static ApiRequestManager sInstance;

    Context mContext;
    RequestQueue mRequestQueue;

    public static synchronized ApiRequestManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ApiRequestManager(context);
        }
        return sInstance;
    }

    private ApiRequestManager(Context context) {
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        mRequestQueue.add(request);
    }

}
