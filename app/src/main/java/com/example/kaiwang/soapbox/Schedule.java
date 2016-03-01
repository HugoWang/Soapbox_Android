package com.example.kaiwang.soapbox;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Schedule extends ActionBarActivity {
    TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        populateSpeechList();
    }

    private void populateSpeechList() {
        ArrayList<Speech> arrayOfSpeeches = Speech.getSpeeches();
        // Create the adapter to convert the array to views
        CustomSpeechAdapter adapter = new CustomSpeechAdapter(this, arrayOfSpeeches);
        // Attach the adapter to a ListView
        emptyText = (TextView)findViewById(R.id.emptyText);
        ListView listView = (ListView) findViewById(R.id.myListView);
        listView.setEmptyView(emptyText);
        listView.setAdapter(adapter);
    }

    float x1,x2;

    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();

                //if left to right sweep event on screen
                if (x1 < x2)
                {
                    Intent goback = new Intent(this, HomeSoapbox.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(goback, bndlanimation);
                }

                // if right to left sweep event on screen
                if (x1 > x2)
                {
                    //do sth.
                }
                break;
            }
        }
        return false;
    }
}
