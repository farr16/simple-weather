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
public class GetWeatherTask extends AsyncTask<String, Void, Void> {
    private MainActivity callActivity;
    private Weather weather;

    public GetWeatherTask(MainActivity activity) {
        callActivity = activity;
        weather = new Weather();
    }

    @Override
    protected Void doInBackground(String... params) {
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

            /*JSONArray weather_array = w_json.getJSONArray("weather");
            JSONObject weather = weather_array.getJSONObject(0);
            String sky_status = weather.getString("description");
            JSONObject temp_press = w_json.getJSONObject("main");
            double temp_curr_K = temp_press.getDouble("temp");
            double humidity = temp_press.getDouble("humidity");
            double temp_min_K = temp_press.getDouble("temp_min");
            double temp_max_K = temp_press.getDouble("temp_max");
            double curr_temp_F = temp_curr_K * 9.0/5.0 - 459.67;
            double temp_min_F = temp_min_K * 9.0/5.0 - 459.67;
            double temp_max_F = temp_max_K * 9.0/5.0 - 459.67;*/

            System.out.println(builder.toString());

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

            System.out.println("Description: " + description);
            System.out.println("Current temperature(K): " + temp_curr_K);
            System.out.println("Temperature min(K): " + temp_min_K);
            System.out.println("Temperature max(K): " + temp_max_K);
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind Speed: " + wind_speed);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Void onPostExecute() {
        callActivity.setWeatherGUI(weather);
        return null;
    }
}
