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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
