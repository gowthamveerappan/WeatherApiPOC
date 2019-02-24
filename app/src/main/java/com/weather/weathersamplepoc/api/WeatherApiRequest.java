package com.weather.weathersamplepoc.api;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import java.util.HashMap;
import java.util.Map;

public class WeatherApiRequest<T> extends JsonRequest<T> {

    final String appId = "MU56E35g";
    final String CONSUMER_KEY = "dj0yJmk9UlQ3UzdlaHlxd3dGJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTIw";
    final String CONSUMER_SECRET = "e17372290ed6a62cb9cff9b42e4d48854a96762f";
    final String baseUrl = "https://api.darksky.net/forecast/0342baffda6f05b8b840f2776552fbc5/";
    private String latAndLng;

    public WeatherApiRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
        this.latAndLng = url;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        return headers;
    }

    @Override
    public String getUrl() {
        return baseUrl + latAndLng ;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            return (Response<T>) Response.success(
                    new String(response.data),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    private T parseResponse(String jsonObject) {
        return null; // Add response parsing here
    }


}
