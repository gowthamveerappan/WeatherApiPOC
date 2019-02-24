package com.weather.weathersamplepoc.presenter;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weathersamplepoc.api.ApiRequestManager;
import com.weather.weathersamplepoc.api.WeatherApiRequest;
import com.weather.weathersamplepoc.plain.Example;
import com.weather.weathersamplepoc.utils.LocationUtils;

import java.io.IOException;

public class WeatherPresenter implements IWeatherContract.IWeatherPresenter,LocationListener {

    IWeatherContract.IWeatherView view;
    LocationUtils locationUtils;

    public WeatherPresenter(IWeatherContract.IWeatherView view){
        this.view = view;
    }

    @Override
    public void onLocationChanged(Location location) {
        view.gotLocation(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void getLocation(Context context) {
        locationUtils = new LocationUtils();
        locationUtils.getLocation(context,this);
    }

    @Override
    public String convertCelciusToFarenheit(double celcius){
        /* Convert Celsius to Fahrenheit */
        float farenheitValue = (float) (celcius * (9f / 5) + 32);
        return String.valueOf(farenheitValue);
    }

    @Override
    public void callWeatherApi(Context context, String latitude, String longitude) {
        ApiRequestManager apiRequestManager = ApiRequestManager.getInstance(context);
        final WeatherApiRequest request = new WeatherApiRequest(Request.Method.GET, latitude+","+longitude, null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                ObjectMapper objectMapper = new ObjectMapper();
                Example exampleObject = null;
                try {
                    exampleObject = objectMapper.readValue(String.valueOf(response),Example.class);
                    view.loadWeatherValues(exampleObject);
                    Log.e(">>>>>>>>>>>>","<<<<<<<<<<"+exampleObject.getCurrently().getHumidity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Add error handling here
                view.showApiError("API FAILURE");
            }
        });
        apiRequestManager.addToRequestQueue(request);
    }
}
