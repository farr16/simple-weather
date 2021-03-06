package com.qwyxand.main.simpleweather;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Matthew on 8/17/2016.
 *
 * Task for making API calls using HTTPConnection and storing the results in a weather object.
 */
public class GetWeatherTask extends AsyncTask<String, Void, Weather> {
    private MainActivity callActivity;
    private Weather weather;

    /* Constructor for GetWeather Task
     * Gets a reference to the MainActivity so the GUI can be updated in onPostExecute
     * Initializes a weather object to be passed back to the MainActivity
     */
    public GetWeatherTask(MainActivity activity) {
        callActivity = activity;
        weather = new Weather();
    }

    @Override
    protected Weather doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while( (inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONObject forecast_json = new JSONObject(builder.toString());
            JSONArray weather_array = forecast_json.getJSONArray("weather");
            String description = weather_array.getJSONObject(0).getString("description");

            JSONObject temp_press = forecast_json.getJSONObject("main");
            double temp_curr_K = temp_press.getDouble("temp");
            double temp_min_K = temp_press.getDouble("temp_min");
            double temp_max_K = temp_press.getDouble("temp_max");
            double humidity = temp_press.getDouble("humidity");

            JSONObject wind = forecast_json.getJSONObject("wind");
            double wind_speed = wind.getDouble("speed");

            weather.setDescription(description);
            weather.setTemp_K(temp_curr_K);
            weather.setLow_K(temp_min_K);
            weather.setHigh_K(temp_max_K);
            weather.setHumidity(humidity);
            weather.setWind_speed(wind_speed);

            return weather;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* Takes the weather object returned after doInBackground and passes it back to the MainActivity
     * so the GUI can be updated with the weather data.
     */
    protected void onPostExecute(Weather w){
        if (w != null) {
            callActivity.setWeatherGUI(w);
        }
    }
}
