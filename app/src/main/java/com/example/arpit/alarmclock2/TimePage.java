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

public class TimePage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static Button timeNextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_page);
        OnClickButtonListenerHour();
        OnClickButtonListenerMin();
        //OnClickButtonListenerAMPM();
        OnClickButtonListenerSwitch();

        //get the spinner from the xml.
        final Spinner hourTimeSpinner = findViewById(R.id.hourTimeSpinner);
        timeNextButton = findViewById(R.id.timeNextButton);
        hourTimeSpinner.setOnItemSelectedListener(this);//
        //create a list of items for the spinner.
        String[] hourTimeMethod = new String[]{"Hour", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15","16","17","18","19","20","21","22","23","24"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        final ArrayAdapter<String> hourTimeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hourTimeMethod);
        //set the spinners adapter to the previously created one.
        hourTimeSpinner.setAdapter(hourTimeAdapter);

        //get the spinner from the xml.
        final Spinner minTimeSpinner = findViewById(R.id.minTimeSpinner);
        //create a list of items for the spinner.
        String[] minTimeMethod = new String[]{"Minute", "00", "05", "10", "15", "20", "30", "45", "50", "55"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> minTimeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, minTimeMethod);
        //set the spinners adapter to the previously created one.
        minTimeSpinner.setAdapter(minTimeAdapter);
        minTimeSpinner.setOnItemSelectedListener(this);

        //get the spinner from the xml.
        //final Spinner ampmTimeSpinner = findViewById(R.id.ampmTimeSpinner);
        //create a list of items for the spinner.
      //  String[] ampmTimeMethod = new String[]{"AM", "PM"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
      //  ArrayAdapter<String> ampmTimeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ampmTimeMethod);
        //set the spinners adapter to the previously created one.
     //   ampmTimeSpinner.setAdapter(ampmTimeAdapter);
     //   ampmTimeSpinner.setOnItemSelectedListener(this);


        timeNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimePage.this, ReadyPage.class);
                Intent sentData = new Intent(TimePage.this, ReadyPage.class);
                intent.putExtra("hourTime", hourTimeSpinner.getSelectedItem().toString());
                intent.putExtra("minTime", minTimeSpinner.getSelectedItem().toString());
              //  intent.putExtra("ampmTime", ampmTimeSpinner.getSelectedItem().toString());
                //startActivity(sentData);
                startActivity(intent);
            }
        });


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void OnClickButtonListenerHour() {


    }

    public void OnClickButtonListenerMin() {

    }

    public void OnClickButtonListenerAMPM() {

    }


    public void OnClickButtonListenerSwitch() {
        timeNextButton = findViewById(R.id.timeNextButton);
        timeNextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(TimePage.this, ReadyPage.class);
                        startActivity(intent);
                    }
                }
        );

    }
}



