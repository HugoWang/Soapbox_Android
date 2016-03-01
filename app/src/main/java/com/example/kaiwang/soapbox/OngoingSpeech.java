package com.example.kaiwang.soapbox;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import android.os.Handler;


/**
 * Created by kaiwang on 15.7.2015.
 */
public class OngoingSpeech extends Activity{

    float x1,x2;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        //TextView display_username = (TextView)findViewById(R.id.display_username);
        //display_username.setText("Username:  " + getIntent().getExtras().getString("theText"));

        //setupConnectionFactory();
        //publishToAMQP();
        setupPubButton();
        setupLikeButton();
        setupDislikeButton();

    }

    void setupPubButton() {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Button send_button = (Button)findViewById(R.id.send_comments_btn);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText)findViewById(R.id.enter_username);
                EditText comment = (EditText) findViewById(R.id.enter_comments);

                String my_username = username.getText().toString();
                String my_comment = comment.getText().toString();

                send_button.startAnimation(animAlpha);

                if (TextUtils.isEmpty(my_username)) {
                    username.setError("Your username cannot be empty.");
                    return;
                }
                if (TextUtils.isEmpty(my_comment)) {
                    comment.setError("Your comment cannot be empty.");
                    return;
                }
                if (!((my_username.matches("")&&(my_comment.matches(""))))){
                    //publishMessage(my_comment);
                    comment.setText("");
                    userDbHelper = new UserDbHelper(context);
                    sqLiteDatabase = userDbHelper.getWritableDatabase();
                    userDbHelper.addInformation(my_username, my_comment, sqLiteDatabase);
                    Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_SHORT).show();
                    userDbHelper.close();
                }
            }
        });
    }

    void setupLikeButton() {
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final ImageView like_button = (ImageView)findViewById(R.id.like_img_btn);
        final int color = Color.parseColor("#FFFFFF"); //The color u want
        final int color2 = Color.parseColor("#69FF63");
        like_button.setColorFilter(color);
        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like_button.isPressed()) {
                    //publishMessage("I like it");
                    like_button.startAnimation(animScale);
                    Toast.makeText(getApplicationContext(), "You liked it!", Toast.LENGTH_SHORT).show();
                    like_button.setEnabled(false);
                    like_button.setColorFilter(color2);
                }
            }
        });
    }

    void setupDislikeButton() {
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final ImageView dislike_button = (ImageView)findViewById(R.id.dislike_img_btn);
        final int color = Color.parseColor("#FFFFFF"); //The color u want
        final int color2 = Color.parseColor("#69FF63");
        dislike_button.setColorFilter(color);
        dislike_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dislike_button.isPressed()) {
                    //publishMessage("I don't like it");
                    dislike_button.startAnimation(animScale);
                    Toast.makeText(getApplicationContext(), "You disliked it!", Toast.LENGTH_SHORT).show();
                    dislike_button.setEnabled(false);
                    dislike_button.setColorFilter(color2);
                }
            }
        });
    }

/*
    Thread publishThread;
    @Override
    protected void onDestroy(){
        super.onDestroy();
        publishThread.interrupt();
    }

    //Create a internal message queue.
    private BlockingDeque<String> queue = new LinkedBlockingDeque<String>();
    void publishMessage(String message){
        //Add a message to internal blocking queue
        try{
            Log.d("", "[q]" + message);
            queue.putLast(message);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    //Create a setup function for the ConnectionFactory
    ConnectionFactory factory = new ConnectionFactory();
    private void setupConnectionFactory() {
        String uri = "CLOUDAMQP_URL";
        try {
            factory.setAutomaticRecoveryEnabled(false);
            factory.setUri(uri);
        } catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    private void publishToAMQP() {
        publishThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Connection connection = factory.newConnection();
                        Channel ch = connection.createChannel();
                        ch.confirmSelect();

                        while(true){
                            String message = queue.takeFirst();
                            try{
                                ch.basicPublish("amq.fanout", "comments", null, message.getBytes());
                                Log.d("", "[s]" + message);
                                ch.waitForConfirmsOrDie();
                            }catch(Exception e){
                                Log.d("","[f] " + message);
                                queue.putFirst(message);
                                throw e;
                            }
                        }
                    }catch(InterruptedException e){
                        break;
                    }catch(Exception e){
                        Log.d("", "Connection broken: " + e.getClass().getName());
                        try {
                            Thread.sleep(5000); //sleep and then try again
                        } catch (InterruptedException e1) {
                            break;
                        }
                    }
                }
            }
        });
        publishThread.start();
    }
*/
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
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation,R.anim.animation2).toBundle();
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
