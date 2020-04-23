package com.example.arpit.alarmclock2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private static ImageButton newAlarmButton;
    private static ImageButton seeAlarmsButton;
    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isServicesOK()){
            init();
        }


        OnClickButtonListenerMaps();
        OnClickButtonListenerAllAlarms();
    }


    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }





   private void init(){
        newAlarmButton = findViewById(R.id.newAlarmButton);
        newAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimePage.class);
                startActivity(intent);
            }
        });
    }
    public void OnClickButtonListenerMaps() {
        newAlarmButton = findViewById(R.id.mapsButton);
        newAlarmButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
    public void OnClickButtonListenerAllAlarms() {
        final int hour = 0;
        final int min = 0;
        seeAlarmsButton = findViewById(R.id.seeAlarmsButton);
        seeAlarmsButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                        intent.putExtra(AlarmClock.EXTRA_MINUTES, min);
                        startActivity(intent);
                    }
                }
        );

    }

}







