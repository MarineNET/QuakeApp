package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Viktor Khon on 8/1/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>{
    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    List<Earthquake> result;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        if (result != null) {
            // Use cached data
            Log.i(LOG_TAG, "This is onStartLoading");
            deliverResult(result);
        } else {
            // We have no data, so kick off loading it
            Log.i(LOG_TAG, "This is onStartLoading");
            forceLoad();
        }
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(LOG_TAG, "This is loadInBackground");
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl.length() < 1 || mUrl == null) {
            return null;
        }

        result = QueryUtils.fetchEarthquakeData(mUrl);

        return result;
    }


}
