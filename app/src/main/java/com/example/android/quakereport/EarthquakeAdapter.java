package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Viktor Khon on 7/26/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    Date newDate;

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {
        super(context, 0, earthquake);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.eqrthquake_item, parent, false);

            Earthquake currentEarthquake = getItem(position);

            TextView magnitudeTextView = (TextView) convertView.findViewById(R.id.tv_magnitude);
            magnitudeTextView.setText(String.valueOf(currentEarthquake.getMagnitude()));

            TextView cityNameTextView = (TextView) convertView.findViewById(R.id.tv_city_name);
            cityNameTextView.setText(currentEarthquake.getCityName());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.tv_occurence_date);
            dateTextView.setText(String.valueOf(new Date(currentEarthquake.getDateStamp())));
        }
        return convertView;
    }
}