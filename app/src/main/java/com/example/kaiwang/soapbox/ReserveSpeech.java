package com.example.kaiwang.soapbox;

import android.app.ActivityOptions;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TimePicker;

public class ReserveSpeech extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_speech);
    }

    public void onStart(){
        super.onStart();
        EditText txtDate = (EditText)findViewById(R.id.reserve_date);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    dialog.show(fragmentTransaction, "DatePicker");
                }
            }
        });

        EditText txtTime = (EditText)findViewById(R.id.reserve_time);
        txtTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    TimeDialog timeDialog = new TimeDialog(v);
                    FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                    timeDialog.show(fragmentTransaction2, "TimePicker");
                }
            }
        });
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
