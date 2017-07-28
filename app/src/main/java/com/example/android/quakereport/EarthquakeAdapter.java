package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Viktor Khon on 7/26/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {



    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {
        super(context, 0, earthquake);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.eqrthquake_item, parent, false);

            // get the position of the Earthquake object
            Earthquake currentEarthquake = getItem(position);

            // Create a new Date object and pass the value in millisecs
            Date newDate = new Date(currentEarthquake.getDateStamp());
            // Use SimpleDateFormat and create a new date format that we want to display in app
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
            // Attach the formatter to the date, and store it in a String
            String dateToDisplay = dateFormatter.format(newDate);

            TextView magnitudeTextView = (TextView) convertView.findViewById(R.id.tv_magnitude);
            magnitudeTextView.setText(String.valueOf(currentEarthquake.getMagnitude()));

            TextView cityNameTextView = (TextView) convertView.findViewById(R.id.tv_city_name);
            cityNameTextView.setText(currentEarthquake.getCityName());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.tv_occurence_date);
            dateTextView.setText(dateToDisplay);
        }
        return convertView;
    }
}