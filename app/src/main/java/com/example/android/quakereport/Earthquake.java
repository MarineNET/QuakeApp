package com.example.android.quakereport;

/**
 * Created by Viktor Khon on 7/26/2017.
 */

public class Earthquake {

    private double mMagnitude;

    private String mPlaceName;

    private long mTimeInMilliseconds;

    private String mUrl;

    public Earthquake(double magnitude, String placeName, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mPlaceName = placeName;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
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

    public String getUrl() {
        return mUrl;
    }
}
