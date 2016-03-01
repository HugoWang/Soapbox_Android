package com.example.kaiwang.soapbox;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class HomeSoapbox extends ActionBarActivity implements View.OnClickListener{

    ImageView shoutbox_icon, calender_icon, speech_icon, profile_icon;
    LinearLayout join_soapbox, check_schedule, reserve_speech, my_profile, log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_soapbox);

        shoutbox_icon = (ImageView)findViewById(R.id.shoutBox);
        speech_icon = (ImageView)findViewById(R.id.speech);
        profile_icon = (ImageView)findViewById(R.id.profile);
        calender_icon = (ImageView)findViewById(R.id.calender);
        int color = Color.parseColor("#FFFFFF"); //The color u want
        shoutbox_icon.setColorFilter(color);
        speech_icon.setColorFilter(color);
        profile_icon.setColorFilter(color);
        calender_icon.setColorFilter(color);

        join_soapbox = (LinearLayout)findViewById(R.id.joinSpeech);
        check_schedule = (LinearLayout)findViewById(R.id.checkSchedule);
        reserve_speech = (LinearLayout)findViewById(R.id.reserveSpeech);
        my_profile = (LinearLayout)findViewById(R.id.myProfile);
        log_out = (LinearLayout)findViewById(R.id.btn_logout);
        join_soapbox.setOnClickListener(this);
        check_schedule.setOnClickListener(this);
        reserve_speech.setOnClickListener(this);
        my_profile.setOnClickListener(this);
        log_out.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        switch(v.getId()){
            case R.id.joinSpeech:{
                join_soapbox.startAnimation(animAlpha);
                startActivity(new Intent(this, OngoingSpeech.class));
                break;
            }
            case R.id.checkSchedule:{
                check_schedule.startAnimation(animAlpha);
                startActivity(new Intent(this, Schedule.class));
                break;
            }

            case R.id.reserveSpeech:{
                reserve_speech.startAnimation(animAlpha);
                startActivity(new Intent(this, ReserveSpeech.class));
                break;
            }

            case R.id.myProfile:{
                my_profile.startAnimation(animAlpha);
                startActivity(new Intent(this, MyProfile.class));
                break;
            }

            case R.id.btn_logout:{
                log_out.startAnimation(animAlpha);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            }

        }
    }

}
