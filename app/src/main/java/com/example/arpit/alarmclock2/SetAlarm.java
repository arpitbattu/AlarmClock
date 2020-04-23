package com.example.arpit.alarmclock2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetAlarm extends AppCompatActivity {

    Button mSetAlarmButton;

    public String hourTimeString, minTimeString
            ,hourReadyString, minReadyString;

    public int hourTimeInt, minTimeInt
            ,hourReadyInt, minReadyInt;

    public int alarmHourInt, alarmMinInt;

    public EditText travelTimeText;

    public int travelTimeInt;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alarm);

        Bundle bundle = getIntent().getExtras();
        hourTimeString = bundle.getString("hourTimeString");
        minTimeString = bundle.getString("minTimeString");
        hourReadyString = bundle.getString("hourReadyString");
        minReadyString = bundle.getString("minReadyString");

        hourTimeInt = Integer.parseInt(hourTimeString);
        minTimeInt = Integer.parseInt(minTimeString);
        hourReadyInt = Integer.parseInt(hourReadyString);
        minReadyInt = Integer.parseInt(minReadyString);

        travelTimeText = (EditText) findViewById(R.id.travelTimeText);
        mSetAlarmButton=(Button)findViewById(R.id.set_alarm_button);
        mSetAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                travelTimeInt = Integer.parseInt(String.valueOf(travelTimeText.getText()));
                int latency = (hourTimeInt - hourReadyInt);
                alarmHourInt = ((latency) - (travelTimeInt));
                alarmMinInt = (minTimeInt - minReadyInt);

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, alarmHourInt);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, alarmMinInt);


                    startActivity(intent);


            }
        });

    }
    }

