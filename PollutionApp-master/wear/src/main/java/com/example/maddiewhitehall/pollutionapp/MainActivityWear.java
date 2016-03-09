package com.example.maddiewhitehall.pollutionapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
//import android.widget.ImageButton;

public class MainActivityWear extends Activity {

    private TextView mTextView;
    private ImageButton airButton, trafficButton, soundButton, buildingButton;
    private Intent intent;
    int soundStatus = Color.rgb(0,153,0), //green
            airStatus = Color.rgb(255,0,0), //red
            buildStatus = Color.rgb(255,0,0), //red
            trafficStatus = Color.rgb(255,128,0); //orange

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                //mTextView = (TextView) stub.findViewById(R.id.text);
                trafficButton = (ImageButton) stub.findViewById(R.id.button1);
                trafficButton.setBackgroundColor(trafficStatus);
                soundButton = (ImageButton) stub.findViewById(R.id.button2);
                soundButton.setBackgroundColor(soundStatus);
                airButton = (ImageButton) stub.findViewById(R.id.button3);
                airButton.setBackgroundColor(airStatus);
                buildingButton = (ImageButton) stub.findViewById(R.id.button4);
                buildingButton.setBackgroundColor(buildStatus);

                trafficButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(MainActivityWear.this, TrafficActivity.class);
                        startActivity(intent);
                    }
                });
                soundButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(MainActivityWear.this, SoundActivity.class);
                        startActivity(intent);
                    }
                });
                airButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(MainActivityWear.this, AirActivity.class);
                        startActivity(intent);
                    }
                });
                buildingButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(MainActivityWear.this, BuildingActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
