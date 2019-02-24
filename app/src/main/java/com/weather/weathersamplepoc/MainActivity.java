package com.weather.weathersamplepoc;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.weathersamplepoc.plain.Example;
import com.weather.weathersamplepoc.presenter.IWeatherContract;
import com.weather.weathersamplepoc.presenter.WeatherPresenter;
import com.weather.weathersamplepoc.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements IWeatherContract.IWeatherView {

    WeatherPresenter weatherPresenter = new WeatherPresenter(this);
    LocationManager locationManager;
    private ProgressDialog dialog;
    private int MY_LOCATION_REQUEST_CODE =1111;

    TextView latitudeTv,longitudeTv,timeZoneTv,tempertureInCeTv,tempertureInFaTv,aTemperatureTv,dewPointTv,windSpeedTv,humidityTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);

        latitudeTv = (TextView)findViewById(R.id.latitudeTv);
        longitudeTv = (TextView)findViewById(R.id.longitudeTv);
        timeZoneTv = (TextView)findViewById(R.id.timezoneTv);
        tempertureInCeTv = (TextView)findViewById(R.id.temperatureInCelciusTv);
        tempertureInFaTv = (TextView)findViewById(R.id.temperatureInFarenTv);
        aTemperatureTv = (TextView)findViewById(R.id.aTemperatureTv);
        dewPointTv = (TextView)findViewById(R.id.dewPointTv);
        windSpeedTv = (TextView)findViewById(R.id.windSpeedTv);
        humidityTv = (TextView)findViewById(R.id.humidityTv);

        //Checking Network connectivity
        if(NetworkUtils.isNetworkConnected(this) == true){
            if(checkLocationPermission() == true){
                weatherPresenter.getLocation(this);
            }
        }else{
            Toast.makeText(this,R.string.offline_text,Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                weatherPresenter.getLocation(this);
            } else {
                this.finish();
            }
        }
    }

    @Override
    public void gotLocation(String latitude, String longitude) {
        latitudeTv.setText(latitude);
        longitudeTv.setText(longitude);
        weatherPresenter.callWeatherApi(MainActivity.this,latitude,longitude);
    }

    @Override
    public void loadWeatherValues(Example responseObj) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        timeZoneTv.setText("" + weatherPresenter.convertTimestamp(responseObj.getCurrently().getTime()));
        tempertureInCeTv.setText("" + responseObj.getCurrently().getTemperature());
        tempertureInFaTv.setText(weatherPresenter.convertCelciusToFarenheit(responseObj.getCurrently().getTemperature()));
        aTemperatureTv.setText("" + responseObj.getCurrently().getApparentTemperature());
        dewPointTv.setText("" + responseObj.getCurrently().getDewPoint());
        windSpeedTv.setText("" + responseObj.getCurrently().getWindSpeed());
        humidityTv.setText("" + responseObj.getCurrently().getHumidity());
    }

    @Override
    public void showApiError(String value) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
