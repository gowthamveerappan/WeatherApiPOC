package com.weather.weathersamplepoc.presenter;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.weather.weathersamplepoc.utils.LocationUtils;

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
}
