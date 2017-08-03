/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    /**
     * Sample JSON response for a USGS query
     */
    private static final String SAMPLE_JSON_RESPONSE =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private ListView earthquakeListView;

    private EarthquakeAdapter mAdapter;

    private TextView emptyView;

    private ProgressBar mProgressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "This is onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Get the list of earthquakes from {@link QueryUtils}
        final ArrayList<Earthquake> earthquakes = null;

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        emptyView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(emptyView);

        mProgressBar1 = (ProgressBar) findViewById(R.id.progressBar3);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        // Store response on whether there is or there is no network connection
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        Log.i(LOG_TAG, "This is initLoader");
        // If there is a network connection, start Loader
        if (isConnected) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            // If no network found, display a text and hide progress bar
            emptyView.setText("No internet connection");
            mProgressBar1.setVisibility(View.INVISIBLE);
        }

        // Set onClickListener to ListView
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the current earthquake that was clicked on
                Earthquake earthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri webpage = Uri.parse(earthquake.getUrl());
                // Create a new intent to view the earthquake URI
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, webpage);
                // Send the intent to launch a new activity
                if (openBrowser.resolveActivity(getPackageManager()) != null) {
                    startActivity(openBrowser);
                }
            }
        });
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "This is onCreateLoader");
        return new EarthquakeLoader(EarthquakeActivity.this, SAMPLE_JSON_RESPONSE);

    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {

        // Set empty state text to display "No earthquakes found."
        emptyView.setText("NO earthquakes found");

        Log.i(LOG_TAG, "This is onLoadFinished");
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {

            mAdapter.addAll(data);

            mProgressBar1.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.i(LOG_TAG, "This is onLoaderReset");
        mAdapter.clear();
    }
}
