package com.weather.weathersamplepoc;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.weather.weathersamplepoc.api.ApiRequestManager;
import com.weather.weathersamplepoc.api.WeatherApiRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.weather.weathersamplepoc", appContext.getPackageName());
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void callApiInstrumentTest() {
        // Context of the app under test.
        Context appContext = getTargetContext();
        ApiRequestManager apiRequestManager = ApiRequestManager.getInstance(appContext);
        final CountDownLatch allDoneSignal = new CountDownLatch(1);
        final WeatherApiRequest request = new WeatherApiRequest(Request.Method.GET, "12.976323,80.217116", null, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Log.e("Response ","::::"+response);
                assertNotNull(response);
                allDoneSignal.countDown();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Add error handling here
                allDoneSignal.countDown();
            }
        });
        apiRequestManager.addToRequestQueue(request);
        try {
            allDoneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
