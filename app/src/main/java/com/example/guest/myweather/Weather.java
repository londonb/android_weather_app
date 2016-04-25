package com.example.guest.myweather;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Guest on 4/25/16.
 */
public class Weather {
    private double mMinTemp;
    private double mMaxTemp;
    private String mDescription;
    private String mDate;

    public Weather(double minTemp, double maxTemp, String description, String date) {
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

    public String getDate() {
        return mDate;
    }
}
