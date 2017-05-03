package hinzehaley.com.weatherapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WeatherLocationActivity extends AppCompatActivity {

    public static final String WEATHER_INFO_FRAGMENT_ID = "weatherInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_location);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment weatherInfoFragment = new WeatherDisplayFragment();
        fragmentTransaction.add(R.id.fragment_container, weatherInfoFragment, WEATHER_INFO_FRAGMENT_ID);
        fragmentTransaction.commit();
    }
}
