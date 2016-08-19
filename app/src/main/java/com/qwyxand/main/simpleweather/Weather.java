package com.qwyxand.main.simpleweather;

/**
 * Created by Matthew on 8/17/2016.
 *
 * Defines a class called Weather.
 *
 * Weather objects are used to store the data displayed on the front page of the app.
 * The data fields included in a weather object are  weather description, temperature, daily highs
 * and lows, humidity, and wind speed.
 */
public class Weather {
    private String description;
    private double temp_K;
    private double high_K;
    private double low_K;
    private double humidity;
    private double wind_speed;

    /* Constructor for the Weather class.
    * Initializes a weather object with empty or zero values for all attributes*/
    public Weather() {
        description = "";
        temp_K = 0.0;
        high_K = 0.0;
        low_K = 0.0;
        humidity = 0.0;
        wind_speed = 0.0;
    }

    public void setDescription(String desc) {description = desc;}

    public void setTemp_K(double temp) {temp_K = temp;}

    public void setHigh_K(double high) {high_K = high;}

    public void setLow_K(double low){low_K = low;}

    public void setHumidity(double humid) {humidity = humid;}

    public void setWind_speed(double wind) {wind_speed = wind;}

    public String getDescription() {return description;}

    public double getTemp_K() {return temp_K;}

    public double getHigh_K() {return high_K;}

    public double getLow_K() {return low_K;}

    public double getHumidity() {return humidity;}

    public double getWind_Speed() {return wind_speed;}

}
