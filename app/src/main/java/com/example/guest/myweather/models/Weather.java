package com.example.guest.myweather.models;



/**
 * Created by Guest on 4/25/16.
 */
public class Weather {
    private String mMinTemp;
    private String mMaxTemp;
    private String mDescription;
    private String mDate;
    private String mWeatherIcon;

    public Weather(String minTemp, String maxTemp, String description, String date, String weatherIcon) {
        this.mMinTemp = minTemp;
        this.mMaxTemp = maxTemp;
        this.mDescription = description;
        this.mDate = date;
        this.mWeatherIcon = weatherIcon;
    }

    public String getMinTemp() {
        return mMinTemp;
    }

    public String getMaxTemp() {
        return mMaxTemp;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDate() {
        return mDate;
    }

    public String getWeatherIcon() {
        return "http://openweathermap.org/img/w/" + mWeatherIcon + ".png";
    }
}
