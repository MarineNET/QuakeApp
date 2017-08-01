package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Viktor Khon on 7/26/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

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

            DecimalFormat formatter = new DecimalFormat("0.0");
            TextView magnitudeTextView = (TextView) convertView.findViewById(R.id.tv_magnitude);
            magnitudeTextView.setText(String.valueOf(formatter.format
                    (currentEarthquake.getMagnitude())));

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
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
            // Attach the formatter to the date, and store it in a String
            String dateToDisplay = dateFormatter.format(newDate);
            dateTextView.setText(dateToDisplay);

            TextView timeTextView = (TextView) convertView.findViewById(R.id.tv_time);
            // Use SimpleDateFormat and create a new time format that we want to display in app
            SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
            // Attach the formatter to the time, and store it in a String
            String timeToDisplay = timeFormatter.format(newDate);
            timeTextView.setText(timeToDisplay);

            // Set the proper background color on the magnitude circle.
            // Fetch the background from the TextView, which is a GradientDrawable.
            GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

            // Get the appropriate background color based on the current earthquake magnitude
            int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

            // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudeColor);
        }
        return convertView;
    }
}