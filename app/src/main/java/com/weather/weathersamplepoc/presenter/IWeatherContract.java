package com.weather.weathersamplepoc.presenter;

import android.content.Context;

public class IWeatherContract {

    public interface IWeatherPresenter {
        public void getLocation(Context context);
    }

    public interface IWeatherView{
        public void gotLocation(String latitude,String longitude);
    }

}
