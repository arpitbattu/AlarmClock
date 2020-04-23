package com.example.arpit.alarmclock2;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;


public class GetDirectionsData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionsData;
    String duration, distance, durationGet;
    LatLng latLng;


    //TextView durationText;
    @Override
    protected String doInBackground(Object... objects) {

        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        latLng = (LatLng)objects[2];



        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }



        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {


        GetDirectionsData d = new GetDirectionsData();
        HashMap<String, String> directionsList;
        DataParser parser = new DataParser();
        directionsList = parser.parseDirections(s);
        duration = directionsList.get("duration");
        distance = directionsList.get("distance");
        d.setDurationMethod(duration);



        mMap.clear();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.draggable(true);
        markerOptions.title("Duration ="+duration);
        markerOptions.snippet("Distance = "+distance);

        mMap.addMarker(markerOptions);



    }

    public void setDurationMethod(String dur){
         durationGet = dur;
    }

    public String getDurationMethod(){
        return duration;

    }






}

