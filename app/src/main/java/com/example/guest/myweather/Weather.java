package com.example.guest.myweather;

/**
 * Created by Guest on 4/25/16.
 */
public class Weather {
    private double mMinTemp;
    private double mMaxTemp;
    private String mDescription;
    private double mDate;

    public Weather(double minTemp, double maxTemp, String description, double date) {
        this.mMinTemp = minTemp;
        this.mMaxTemp = maxTemp;
        this.mDescription = description;
        this.mDate = date;
    }

    public double getMinTemp() {
        return mMinTemp;
    }

    public double getMaxTemp() {
        return mMaxTemp;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getDate() {
        return mDate;
    }
}
