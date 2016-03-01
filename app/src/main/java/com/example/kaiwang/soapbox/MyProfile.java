package com.example.kaiwang.soapbox;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyProfile extends ActionBarActivity {

    TextView emptyProText;
    ListView profilelistView;
    SQLiteDatabase sqLiteDatabase;
    UserDbHelper userDbHelper;
    Cursor cursor;
    ListProfileAdapter listProfileAdapter;
    TextView user_imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profilelistView = (ListView)findViewById(R.id.profileView);
        emptyProText = (TextView)findViewById(R.id.emptyProfileText);
        listProfileAdapter =new ListProfileAdapter(getApplicationContext(), R.layout.profile_row);
        profilelistView.setAdapter(listProfileAdapter);

        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getInformation(sqLiteDatabase);

        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = null;
        if(tm != null){
            device_id = tm.getDeviceId();
        }
        user_imei = (TextView)findViewById(R.id.profile_user_imei);
        user_imei.setText(device_id);

        if(cursor.moveToFirst()){
            do{

                String name, id;
                name = cursor.getString(0);
                id = cursor.getString(1);
                DataProvider dataProvider = new DataProvider(name, id);
                listProfileAdapter.add(dataProvider);
                profilelistView.setEmptyView(emptyProText);

            }while(cursor.moveToNext());
        }

        //list.add("Username:  " + getIntent().getExtras().getString("theName"));
        //list.add("ID:  " + getIntent().getExtras().getString("theId"));

/*
        if (profilelistView.getMaxScrollAmount() == 0){
            Log.d("Test", "null 1");
            profilelistView.setEmptyView(emptyProText);
            Log.d("Test", "hello");
        }
*/

    }

    /*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (adapter.getItem(position).contains("Log out")){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
    */

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
