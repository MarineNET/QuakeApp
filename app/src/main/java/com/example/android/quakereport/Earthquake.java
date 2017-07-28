package com.example.android.quakereport;

/**
 * Created by Viktor Khon on 7/26/2017.
 */

public class Earthquake {

    private double mMagnitude;

    private String mCityName;

    private long mDateStamp;

    public Earthquake(double magnitude, String cityName, long dateStamp) {
        mMagnitude = magnitude;
        mCityName = cityName;
        mDateStamp = dateStamp;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getCityName() {
        return mCityName;
    }

    public long getDateStamp() {
        return mDateStamp;
    }

}
