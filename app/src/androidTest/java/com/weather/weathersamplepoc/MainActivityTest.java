package com.weather.weathersamplepoc;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.weather.weathersamplepoc.utils.NetworkUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void checkNetworkConnection() {
        assertTrue(NetworkUtils.isNetworkConnected(InstrumentationRegistry.getTargetContext()));
    }

    @After
    public void tearDown() throws Exception {
    }
}
