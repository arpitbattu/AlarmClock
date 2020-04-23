package com.example.arpit.alarmclock2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ReadyPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String hourTime, minTime, ampmTime
            ,hourReady, minReady
            ,fromDest, destAddress;



    public static Button readyButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_page);

        Bundle bundle = getIntent().getExtras();
        hourTime = bundle.getString("hourTime");
        minTime = bundle.getString("minTime");
        ampmTime = bundle.getString("ampmTime");

        //get the spinner from the xml.
        readyButton = findViewById(R.id.readyButton);
        final Spinner readyHourSpinner = findViewById(R.id.readyHourSpinner);
        final Spinner readyMinSpinner = findViewById(R.id.readyMinSpinner);

        //create a list of items for the spinner.
        String[] hourSpinner = new String[]{"Hours", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] minSpinner = new String[]{"Minutes", "00", "15", "30", "45"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> hourAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hourSpinner);
        ArrayAdapter<String> minAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, minSpinner);
        //set the spinners adapter to the previously created one.
        readyHourSpinner.setAdapter(hourAdapter);
        readyHourSpinner.setOnItemSelectedListener(this);

        readyMinSpinner.setAdapter(minAdapter);
        readyMinSpinner.setOnItemSelectedListener(this);

        //OnClickButtonListener();

        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadyPage.this, DestPage.class);
                Intent sentData = new Intent(ReadyPage.this, DestPage.class);
                intent.putExtra("hourTime", hourTime);
                intent.putExtra("minTime", minTime);
                intent.putExtra("ampmTime", ampmTime);
                intent.putExtra("hourReady", String.valueOf(readyHourSpinner.getSelectedItem()));
                intent.putExtra("minReady", String.valueOf(readyMinSpinner.getSelectedItem()));
                //intent.putExtra("ampmTime", String.valueOf(ampmTimeSpinner.getSelectedItem()));
                //startActivity(sentData);
                startActivity(intent);
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }



   /* public void OnClickButtonListener(){
        readyButton = findViewById(R.id.readyButton);
        readyButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ReadyPage.this, DestPage.class);
                        startActivity(intent);
                    }
                }


        ); */

    }
//}