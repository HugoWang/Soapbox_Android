package com.example.kaiwang.soapbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kaiwang on 20.8.2015.
 */
public class CustomSpeechAdapter extends ArrayAdapter<Speech> {
    public CustomSpeechAdapter(Context context, ArrayList<Speech> speeches) {
        super(context, 0, speeches);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Speech speech = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.schedule_list_item, parent, false);
        }
        // Lookup view for data population
        TextView myTopic = (TextView) convertView.findViewById(R.id.topic_item);
        TextView myName = (TextView) convertView.findViewById(R.id.speaker_item);
        TextView myTime = (TextView) convertView.findViewById(R.id.time_item);
        // Populate the data into the template view using the data object
        myTopic.setText("Topic: "+ speech.topic);
        myName.setText("Speaker: " + speech.speaker);
        myTime.setText("Time: " + speech.time);
        // Return the completed view to render on screen
        return convertView;
    }
}
