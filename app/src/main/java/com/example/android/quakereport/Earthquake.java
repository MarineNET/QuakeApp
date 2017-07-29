package com.example.android.quakereport;

/**
 * Created by Viktor Khon on 7/26/2017.
 */

public class Earthquake {

    private double mMagnitude;

    private String mPlaceName;

    private long mTimeInMilliseconds;

    public Earthquake(double magnitude, String placeName, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mPlaceName = placeName;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

}
