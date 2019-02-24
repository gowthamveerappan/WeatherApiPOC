package com.weather.weathersamplepoc.presenter;

import android.content.Context;

import com.weather.weathersamplepoc.plain.Example;

public class IWeatherContract {

    public interface IWeatherPresenter {
        public void getLocation(Context context);
        public void callWeatherApi(Context context,String latitude,String longitude);
        public String convertCelciusToFarenheit(double celciusValue);
    }

    public interface IWeatherView{
        public void gotLocation(String latitude,String longitude);
        public void loadWeatherValues(Example responseObj);
        public void showApiError(String value);
    }

}
