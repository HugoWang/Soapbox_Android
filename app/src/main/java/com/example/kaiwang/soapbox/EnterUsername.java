package com.example.kaiwang.soapbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by kaiwang on 20.7.2015.
 */
public class EnterUsername extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        //setupConnectionFactory();
        //publishToAMQP();
        setupPubButton();
    }

    void setupPubButton() {
        Button send_username_btn = (Button)findViewById(R.id.send_username_btn);
        send_username_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText)findViewById(R.id.enter_username);
                String my_username = username.getText().toString();
                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String device_id = null;
                if(tm != null){
                    device_id = tm.getDeviceId();
                }
                if(TextUtils.isEmpty(my_username)){
                    username.setError("Your username cannot be empty.");
                    return;
                }else{
                    //publishMessage(device_id + my_username);
                    Toast.makeText(getApplicationContext(), device_id, Toast.LENGTH_SHORT).show();
                    Intent comment_intent = new Intent(getApplicationContext(),OngoingSpeech.class);
                    comment_intent.putExtra("theText", my_username);
                    startActivity(comment_intent);
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
}
