package com.example.kaiwang.soapbox;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by kaiwang on 28.8.2015.
 */
public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener  {
    EditText textTime;

    public TimeDialog(View view){
        textTime = (EditText)view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

    public void onTimeSet(TimePicker view, int hour, int minute){
        if ((hour > 10) && (minute < 10)){
            String time = hour + ":0" + minute;
            textTime.setText(time);
        }
        else if ((hour > 10) && (minute > 10)){
            String time = hour + ":" + minute;
            textTime.setText(time);
        }
        else if ((hour < 10) && (minute > 10)){
            String time = "0" + hour + ":" + minute;
            textTime.setText(time);
        }
        else {
            String time = "0" + hour + ":0" + minute;
            textTime.setText(time);
        }

    }
}
