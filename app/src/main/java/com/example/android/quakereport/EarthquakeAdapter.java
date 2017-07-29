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

            TextView magnitudeTextView = (TextView) convertView.findViewById(R.id.tv_magnitude);
            magnitudeTextView.setText(String.valueOf(currentEarthquake.getMagnitude()));

            // Extract a full name of the place from Earthquake class
            String name = currentEarthquake.getPlaceName();
            // Create String values to hold names for offset location and name of a city
            String offset;
            String cityName;

            // Get an index location when the word "of" starts
            int index = name.indexOf("of");

            if (index != -1) {
                // If 'name' has the String "of" then:
                // offsite = from 0 index to the beginning of "of" and add 3 indexes to make sure
                // the word "of" is actually included as well
                offset = name.substring(0, index+3);
                // cityName = begin when "of" finishes until the last word in the String
                cityName = name.substring(index+3, name.length());
            } else {
                // If "of" is not present, make offset return a specific String value
                offset = getContext().getString(R.string.near_the);
                // Make cityName start from the beginning until the end of the String
                cityName = name.substring(0, name.length());
            }

            // Assign appropriate text to locationOffset TextView using String offset
            TextView locationOffset = (TextView) convertView.findViewById(R.id.tv_location_offset);
            locationOffset.setText(offset);

            // Assign appropriate text to primaryLocation TextView using String cityName
            TextView primaryLocation = (TextView) convertView.findViewById(R.id.tv_primary_location);
            primaryLocation.setText(cityName);

            // Create a new Date object and pass the value in millisecs
            Date newDate = new Date(currentEarthquake.getTimeInMilliseconds());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.tv_date);
            // Use SimpleDateFormat and create a new date format that we want to display in app
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
            // Attach the formatter to the date, and store it in a String
            String dateToDisplay = dateFormatter.format(newDate);
            dateTextView.setText(dateToDisplay);

            TextView timeTextView = (TextView) convertView.findViewById(R.id.tv_time);
            // Use SimpleDateFormat and create a new time format that we want to display in app
            SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
            // Attach the formatter to the time, and store it in a String
            String timeToDisplay = timeFormatter.format(newDate);
            timeTextView.setText(timeToDisplay);
        }
        return convertView;
    }
}