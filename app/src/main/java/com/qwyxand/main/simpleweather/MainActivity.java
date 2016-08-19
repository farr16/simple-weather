package com.qwyxand.main.simpleweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
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

    private boolean temp_C = false;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weather_description = (TextView) findViewById(R.id.weather_description_display);
        current_temperature = (TextView) findViewById(R.id.current_temp_display);
        daily_low = (TextView) findViewById(R.id.daily_low_display);
        daily_high = (TextView) findViewById(R.id.daily_high_display);
        humidity = (TextView) findViewById(R.id.humidity_display);
        wind_speed = (TextView) findViewById(R.id.wind_speed_display);
        temp_toggle = (Switch) findViewById(R.id.temp_format_switch);

        weather = null;

        temp_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    temp_C = true;
                }
                else
                {
                    temp_C = false;
                }
            }
        });

        String base_url = "http://api.openweathermap.org/data/2.5/weather?id=";
        String suffix_url = "&APPID=";
        //this will be replaced with a dynamic means of setting location, but will be used for tests
        String city_id = "5751632";

        String api_call_url = base_url + city_id + suffix_url + BuildConfig.OPEN_WEATHER_MAP_API_KEY;

        new GetWeatherTask(this).execute(api_call_url);

    }

    public void setWeatherGUI(Weather w) {
        weather_description.setText(w.getDescription());
        String curr_temp_text = "" + convertKelvin(w.getTemp_K(), temp_C);
        current_temperature.setText(curr_temp_text);
        String low_text = "" + convertKelvin(w.getLow_K(), temp_C);
        daily_low.setText(low_text);
        String high_text = "" + convertKelvin(w.getHigh_K(), temp_C);
        daily_high.setText(high_text);
        String humidity_text = "Humidity: " + w.getHumidity() + "%";
        humidity.setText(humidity_text);
        String wind_speed_text = "Wind Speed: " + w.getWind_Speed();
        wind_speed.setText(wind_speed_text);

        weather = w;
    }

    private String convertKelvin(double temp, boolean b) {
        if(b) {
            //return "" + (temp - 273.15);
            return String.format("%.2g%n", (temp - 273.15));
        }
        else {
            //return "" + (temp * 9/5 - 459.67);
            return String.format("%.2g%n", (temp * 9/5 - 459.67));
        }
    }
}
