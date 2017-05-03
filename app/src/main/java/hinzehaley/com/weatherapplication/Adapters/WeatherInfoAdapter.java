package hinzehaley.com.weatherapplication.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hinzehaley.com.weatherapplication.R;
import hinzehaley.com.weatherapplication.WeatherInfo;

/**
 * Created by haleyhinze on 5/3/17.
 */

public class WeatherInfoAdapter extends RecyclerView.Adapter<WeatherInfoAdapter.ViewHolder>{



        private ArrayList<WeatherInfo> weatherInfo;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView title;
            public TextView desc;


            public ViewHolder(LinearLayout v) {
                super(v);
                title = (TextView) v.findViewById(R.id.txt_title);
                desc = (TextView) v.findViewById(R.id.txt_desc);
            }

            public void setTitle(String title){
                this.title.setText(title);
            }

            public void setDesc(String desc){
                this.desc.setText(desc);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public WeatherInfoAdapter(ArrayList<WeatherInfo> weatherInfo) {
            this.weatherInfo = weatherInfo;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public WeatherInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_view_weather, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.setTitle(weatherInfo.get(position).dayOfWeek);
            holder.setDesc(weatherInfo.get(position).weatherDescription);

            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            //holder.mTextView.setText(mDataset[position]);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return weatherInfo.size();
        }

        public void updateWeatherInfo(ArrayList<WeatherInfo> weatherInfo){
            this.weatherInfo = weatherInfo;
        }



}
