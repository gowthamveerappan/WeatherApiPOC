package com.weather.weathersamplepoc;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.weather.weathersamplepoc.utils.NetworkUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = activityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = activity.findViewById(R.id.temperatureInCelciusTv);
        assertNotNull(view);
    }

    @Test
    public void testApi() {
        activity.weatherPresenter.callWeatherApi(InstrumentationRegistry.getTargetContext(),"12.976323","80.217116");

        assertNotSame("-",((TextView)activity.findViewById(R.id.latitudeTv)).getText());
        assertNotSame("-",((TextView)activity.findViewById(R.id.longitudeTv)).getText());
        assertNotSame("-",((TextView)activity.findViewById(R.id.temperatureInCelciusTv)).getText());
        assertNotSame("-",((TextView)activity.findViewById(R.id.temperatureInFarenTv)).getText());
        assertNotSame("-",((TextView)activity.findViewById(R.id.aTemperatureTv)).getText());
        assertNotSame("-",((TextView)activity.findViewById(R.id.windSpeedTv)).getText());
        assertNotSame("-",((TextView)activity.findViewById(R.id.timezoneTv)).getText());
        assertNotSame("-",((TextView)activity.findViewById(R.id.dewPointTv)).getText());
        assertNotSame("-",((TextView)activity.findViewById(R.id.humidityTv)).getText());
    }

    @Test
    public void checkNetworkConnection() {
        assertTrue(NetworkUtils.isNetworkConnected(InstrumentationRegistry.getTargetContext()));
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}
