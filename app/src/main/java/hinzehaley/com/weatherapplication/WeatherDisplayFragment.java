package hinzehaley.com.weatherapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hinzehaley.com.weatherapplication.Adapters.WeatherInfoAdapter;


/**
 * Fragment for displaying weather forecast. Created as a fragment to allow it to be reused
 * in different places
 */
public class WeatherDisplayFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private WeatherInfoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<WeatherInfo> weatherData = new ArrayList<WeatherInfo>();



    public WeatherDisplayFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //retain for rotation
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_weather_display, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new WeatherInfoAdapter(weatherData, getContext());
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Called when new weather info is available. Called from WeatherLocationActivity
     * @param weatherInfo
     */
    public void updateWeatherInfo(ArrayList<WeatherInfo> weatherInfo){
        mAdapter.updateWeatherInfo(weatherInfo);
        mAdapter.notifyDataSetChanged();
    }

}
