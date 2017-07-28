package com.example.android.quakereport;

/**
 * Created by Viktor Khon on 7/26/2017.
 */

public class Earthquake {

    private double mMagnitude;

    private String mCityName;

    private long mTimeInMilliseconds;

    public Earthquake(double magnitude, String cityName, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mCityName = cityName;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getCityName() {
        return mCityName;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

}
