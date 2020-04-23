package com.example.arpit.alarmclock2;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MethodPage extends AppCompatActivity{
    private static Button methodNextButton;
    public TextView testText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method_page);
        OnClickListener();

        testText = findViewById(R.id.testText);

        //get the spinner from the xml.
        Spinner transSpinner = findViewById(R.id.methodSpinner);
        //create a list of items for the spinner.
        String[] transMethod = new String[]{"Driving", "Transit", "Walking", "Cycling"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> transAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, transMethod);
        //set the spinners adapter to the previously created one.
        transSpinner.setAdapter(transAdapter);
    }
    public void OnClickListener(){
        methodNextButton = findViewById(R.id.methodNextButton);
        methodNextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MethodPage.this, TimePage.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
