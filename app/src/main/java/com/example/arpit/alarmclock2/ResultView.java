package com.example.arpit.alarmclock2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultView extends AppCompatActivity {
    private String hourTime, minTime, ampmTime
                    ,hourReady, minReady
                    ,fromDest, destAddress;

    private TextView hourTimeText, minTimeText, amTimeText
                        ,hourReadyText, minReadyText
                        ,fromDestText, destTextView
                        ,addressTV, latLongTV, editText;

    private Button addressButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_view);
        Bundle bundle = getIntent().getExtras();
        hourTime = bundle.getString("hourTime");
        minTime = bundle.getString("minTime");
       // ampmTime = bundle.getString("ampmTime");

        hourReady = bundle.getString("hourReady");
        minReady = bundle.getString("minReady");

        fromDest = bundle.getString("fromDest");
        destAddress = bundle.getString("destAddress");

        hourTimeText = (TextView) findViewById(R.id.hourTimeText);
        minTimeText = (TextView) findViewById(R.id.minTimeText);
        //amTimeText = (TextView) findViewById(R.id.amTimeText);

        hourReadyText = (TextView) findViewById(R.id.hourReadyText);
        minReadyText = (TextView) findViewById(R.id.minReadyText);

        fromDestText = (TextView) findViewById(R.id.fromDestText);
        destTextView = (TextView) findViewById(R.id.destTextView);

        hourTimeText.setText("Hour: "+hourTime);
        minTimeText.setText("Minute: "+ minTime);
        // amTimeText.setText(ampmTime);

        hourReadyText.setText("Hour: "+hourReady);
        minReadyText.setText("Minute: "+minReady);

        fromDestText.setText(fromDest);
        destTextView.setText(destAddress);

      //  addressTV = (TextView) findViewById(R.id.addressTV);
      //  latLongTV = (TextView) findViewById(R.id.latLongTV);

        //nextButton = (Button) findViewById(R.id.nextButton);
        addressButton = (Button) findViewById(R.id.addressButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                //TextView editText = (TextView) findViewById(R.id.editText);
                String fromAddress = fromDest;
                String toAddress = destAddress;

                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getFromAddressFromLocation(fromAddress,
                        getApplicationContext(), new FromGeocoderHandler());
                locationAddress.getToAddressFromLocation(destAddress,
                        getApplicationContext(), new ToGeocoderHandler());


            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(ResultView.this, SetAlarm.class);
                intent.putExtra("hourTimeString", hourTime);
                intent.putExtra("minTimeString", minTime);
                intent.putExtra("hourReadyString", hourReady);
                intent.putExtra("minReadyString", minReady);
                startActivity(intent);

            }
        });

    }

    private class FromGeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            fromDestText.setText(locationAddress);
        }
    }
    private class ToGeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            destTextView.setText(locationAddress);
        }
    }




}

