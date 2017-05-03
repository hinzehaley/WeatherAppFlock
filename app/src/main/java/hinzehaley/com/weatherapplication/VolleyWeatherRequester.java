package hinzehaley.com.weatherapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hinzehaley.com.weatherapplication.Listeners.WeatherRetrievedListener;

/**
 * Created by haleyhinze on 5/3/17.
 */

public class VolleyWeatherRequester {

    static RequestQueue queue = null;

    private static VolleyWeatherRequester requester;

    public static VolleyWeatherRequester getInstance(){
        if(requester == null){
            requester = new VolleyWeatherRequester();
        }
        return requester;
    }

    private VolleyWeatherRequester(){

    }


    public void requestWeather(final Context context, String key, String state, String city, final WeatherRetrievedListener listener) {

        //Builds request URLs
        String urlPath = "http://api.wunderground.com/api/" + key+"/forecast/q/" + state +"/" + city +".json";
        // Instantiate the RequestQueue.
        if(queue == null) {
            queue = Volley.newRequestQueue(context);
        }


        // Request a string response from the provided URL. Passes reponse into callerFragment
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlPath,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject forecast = jsonObject.getJSONObject("forecast").getJSONObject("txt_forecast");
                            JSONArray forecastDayArr = forecast.getJSONArray("forecastday");
                            ArrayList<WeatherInfo> weatherArr = new ArrayList<>();
                            for(int i = 0; i < forecastDayArr.length(); i++){
                                JSONObject forecastDay = forecastDayArr.getJSONObject(i);
                                String title = forecastDay.getString("title");
                                String iconUrl = forecastDay.getString("icon_url");
                                String desc = forecastDay.getString("fcttext");
                                WeatherInfo weatherInfo = new WeatherInfo(title, iconUrl, desc);
                                weatherArr.add(weatherInfo);
                            }

                            Log.i("REQUEST", "updating listener : " + weatherArr.size());

                            listener.weatherRetrieved(weatherArr);

                        } catch (JSONException e) {
                            Log.i("REQUEST", "error parsing json: " + e.getMessage());
                            e.printStackTrace();
                        }

                        // Display the first 500 characters of the response string.

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("REQUEST", "error response is: " + error.getMessage());

                //TODO: handle error response


            }
        });

        queue.add(stringRequest);
    }

}
