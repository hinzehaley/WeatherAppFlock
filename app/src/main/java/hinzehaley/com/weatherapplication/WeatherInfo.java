package hinzehaley.com.weatherapplication;

/**
 * Created by haleyhinze on 5/3/17.
 */

public class WeatherInfo {

    public String imageUrl;
    public String weatherDescription;
    public String dayOfWeek;

    public WeatherInfo(String title, String imageUrl, String desc){
        this.dayOfWeek = title;
        this.imageUrl = imageUrl;
        this.weatherDescription = desc;
    }



}
