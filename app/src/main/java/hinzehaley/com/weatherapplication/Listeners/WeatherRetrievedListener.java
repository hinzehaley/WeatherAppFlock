package hinzehaley.com.weatherapplication.Listeners;

import java.util.ArrayList;

import hinzehaley.com.weatherapplication.WeatherInfo;

/**
 * Created by haleyhinze on 5/3/17.
 */

public interface WeatherRetrievedListener {

    void weatherRetrieved(ArrayList<WeatherInfo> weatherInfo);
}
