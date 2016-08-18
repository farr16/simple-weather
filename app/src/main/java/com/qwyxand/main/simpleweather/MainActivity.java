package com.qwyxand.main.simpleweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView weather_description;
    private TextView current_temperature;
    private TextView daily_low;
    private TextView daily_high;
    private TextView humidity;
    private TextView wind_speed;
    private Switch temp_toggle;

    private String base_url = "http://api.openweathermap.org/data/2.5/weather?id=";
    private String suffix_url = "&APPID=";
    // This will be replaced with a dynamic means of setting location, but will be used for testing
    private String city_id = "5751632";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BuildConfig.OPEN_WEATHER_API_KEY

        weather_description = (TextView) findViewById(R.id.weather_description_display);
        current_temperature = (TextView) findViewById(R.id.current_temp_display);
        daily_low = (TextView) findViewById(R.id.daily_low_display);
        daily_high = (TextView) findViewById(R.id.daily_high_display);
        humidity = (TextView) findViewById(R.id.humidity_display);
        wind_speed = (TextView) findViewById(R.id.humidity_display);
        temp_toggle = (Switch) findViewById(R.id.temp_format_switch);

        //test code for making sure the url is created properly
        String api_call_url = base_url + city_id + suffix_url + BuildConfig.OPEN_WEATHER_MAP_API_KEY;

        weather_description.setText(api_call_url);

    }

    public void setWeatherGUI(Weather w) {
        return;
    }
}
