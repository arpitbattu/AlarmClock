package com.example.arpit.alarmclock2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;




public class DestPage extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {
    private static Button destNextButton;
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private static String LOG_TAG = "DestPage";
    private AutoCompleteTextView fromDest;
    private AutoCompleteTextView destAddress;
    private TextView destText;
    private GoogleApiClient mGoogleApiClient;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));

    private String hourTime, minTime, ampmTime
            ,hourReady, minReady;


    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.dest_page);

        Bundle bundle = getIntent().getExtras();
        hourTime = bundle.getString("hourTime");
        minTime = bundle.getString("minTime");
        ampmTime = bundle.getString("ampmTime");

        hourReady = bundle.getString("hourReady");
        minReady = bundle.getString("minReady");

        mGoogleApiClient = new GoogleApiClient.Builder(DestPage.this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, GOOGLE_API_CLIENT_ID, this)
                .addConnectionCallbacks(this)
                .build();
        fromDest = (AutoCompleteTextView) findViewById(R.id
                .fromDest);
        destAddress = (AutoCompleteTextView) findViewById(R.id
                .destAddress);
        fromDest.setThreshold(3);
        destAddress.setThreshold(3);
        destText = (TextView) findViewById(R.id.destText);
        fromDest.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                BOUNDS_MOUNTAIN_VIEW, null);
        fromDest.setAdapter(mPlaceArrayAdapter);
        destAddress.setAdapter(mPlaceArrayAdapter);

        OnClickListener();
        //get the spinner from the xml.
        Spinner transSpinner = findViewById(R.id.methodSpinner);
        //create a list of items for the spinner.
        String[] transMethod = new String[]{"Driving", "Transit", "Walking", "Cycling"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> transAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, transMethod);
        //set the spinners adapter to the previously created one.
        transSpinner.setAdapter(transAdapter);

        //fer:  retrieve mode  to use in calculate distance
      // String mode=bundle.getString("Transportation mode");


    }

    public void OnClickListener(){
        destNextButton = findViewById(R.id.destNextButton);
        destNextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bundle bundle = getIntent().getExtras();
                        String address = bundle.getString("address");
                        //testText.setText(address);
                        Intent intent = new Intent(DestPage.this, ResultView.class);
                        //Intent sentData = new Intent(DestPage.this, ResultView.class);
                       // startActivity(intent);
                        intent.putExtra("hourTime", hourTime);
                        intent.putExtra("minTime", minTime);
                        intent.putExtra("ampmTime", ampmTime);
                        intent.putExtra("hourReady", hourReady);
                        intent.putExtra("minReady", minReady);
                        String fromDestText = fromDest.getText().toString();
                        String destAddressText = destAddress.getText().toString();
                        intent.putExtra("fromDest", fromDestText);
                        intent.putExtra("destAddress", destAddressText);
                        //startActivity(sentData);
                        startActivity(intent);



                    }
                }
        );
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(LOG_TAG, "Selected: " + item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.i(LOG_TAG, "Fetching details for ID: " + item.placeId);
        }
    };
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e(LOG_TAG, "Place query did not complete. Error: " +
                        places.getStatus().toString());
                places.release(); //fer
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();

           /*mNameTextView.setText(Html.fromHtml(place.getName() + ""));
            mAddressTextView.setText(Html.fromHtml(place.getAddress() + ""));
            mIdTextView.setText(Html.fromHtml(place.getId() + ""));
            mPhoneTextView.setText(Html.fromHtml(place.getPhoneNumber() + ""));
            mWebTextView.setText(place.getWebsiteUri() + "");
            if (attributions != null) {
                mAttTextView.setText(Html.fromHtml(attributions.toString()));
            } */
        }
    };

    @Override
    public void onConnected(Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
        Log.i(LOG_TAG, "Google Places API connected.");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
        Log.e(LOG_TAG, "Google Places API connection suspended.");
    }
}