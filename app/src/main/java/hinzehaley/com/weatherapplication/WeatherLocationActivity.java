package hinzehaley.com.weatherapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import hinzehaley.com.weatherapplication.Listeners.WeatherRetrievedListener;

public class WeatherLocationActivity extends AppCompatActivity implements WeatherRetrievedListener {

    WeatherDisplayFragment weatherInfoFragment;

    public static final String WEATHER_INFO_FRAGMENT_ID = "weatherInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_location);

        VolleyWeatherRequester weatherRequester = VolleyWeatherRequester.getInstance();
        weatherRequester.requestWeather(this, getString(R.string.weather_api_key), "CA", "San_Francisco", this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        weatherInfoFragment = new WeatherDisplayFragment();
        fragmentTransaction.add(R.id.fragment_container, weatherInfoFragment, WEATHER_INFO_FRAGMENT_ID);
        fragmentTransaction.commit();


    }

    @Override
    public void weatherRetrieved(ArrayList<WeatherInfo> weatherInfo) {
        weatherInfoFragment.updateWeatherInfo(weatherInfo);
    }
}
