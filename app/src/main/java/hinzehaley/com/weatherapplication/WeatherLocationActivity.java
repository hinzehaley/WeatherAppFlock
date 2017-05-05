package hinzehaley.com.weatherapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import hinzehaley.com.weatherapplication.Listeners.WeatherRetrievedListener;

public class WeatherLocationActivity extends AppCompatActivity implements WeatherRetrievedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    WeatherDisplayFragment weatherInfoFragment;
    Location mLocation;
    GoogleApiClient mGoogleApiClient;


    public static final String WEATHER_INFO_FRAGMENT_ID = "weatherInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_location);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        VolleyWeatherRequester weatherRequester = VolleyWeatherRequester.getInstance();
        weatherRequester.requestWeather(this, getString(R.string.weather_api_key), "CA", "San_Francisco", this);

        FragmentManager fragmentManager = getSupportFragmentManager();


        weatherInfoFragment = (WeatherDisplayFragment) fragmentManager.findFragmentByTag(WEATHER_INFO_FRAGMENT_ID);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (weatherInfoFragment == null) {
            weatherInfoFragment = new WeatherDisplayFragment();
            fragmentTransaction.add(R.id.fragment_container, weatherInfoFragment, WEATHER_INFO_FRAGMENT_ID);
            fragmentTransaction.commit();
        }
    }

    /**
     * Called from VolleyWeatherRequester when new weather info is retrieved
     * @param weatherInfo
     */
    @Override
    public void weatherRetrieved(ArrayList<WeatherInfo> weatherInfo) {
        weatherInfoFragment.updateWeatherInfo(weatherInfo);
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }

        mLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLocation != null) {
            requestAddress();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void requestAddress(){
        //TODO: implement lat lng to address
    }
}
