package com.weather.weathersamplepoc;

import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.weather.weathersamplepoc.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Checking Network connectivity
        if(NetworkUtils.isNetworkConnected(this) == true){

        }else{
            Toast.makeText(this,R.string.offline_text,Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
}
