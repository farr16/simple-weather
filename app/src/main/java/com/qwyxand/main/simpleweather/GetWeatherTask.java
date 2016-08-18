package com.qwyxand.main.simpleweather;

import android.os.AsyncTask;

import org.json.JSONObject;
import org.json.JSONArray;

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

        return null;
    }

    protected Void onPostExecute() {
        callActivity.setWeatherGUI(weather);
        return null;
    }
}
