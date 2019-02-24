package com.weather.weathersamplepoc;

import com.weather.weathersamplepoc.presenter.IWeatherContract;
import com.weather.weathersamplepoc.presenter.WeatherPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    IWeatherContract.IWeatherView view;

    IWeatherContract.IWeatherPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new WeatherPresenter(view);
    }

    @Test
    public void convertTimeStampToFormat() {
        int timeStampDefault = 1551026740;
        String expectedOutput = "19-01-1970 04:20:26";
        String output = presenter.convertTimestamp(timeStampDefault);
        assertEquals(output,expectedOutput);
    }

    @Test
    public void celciusConverterTest() {
        float celciusSample = 90;
        float farenheitValue = (float) (celciusSample * (9f / 5) + 32);
        String output = presenter.convertCelciusToFarenheit(celciusSample);
        assertEquals(String.valueOf(farenheitValue),output);
    }

    @After
    public void tearDown() throws Exception {
        presenter = null;
    }
}