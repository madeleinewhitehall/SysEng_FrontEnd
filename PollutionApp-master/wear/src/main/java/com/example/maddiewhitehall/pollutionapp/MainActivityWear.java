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
    int air = 0, traffic = 0, build = 0, sound = 0; 
    int nullStatus = Color.rgb(60,60,60), //grey
        low = Color.rgb(0,153,0), //green
        medium = Color.rgb(255,128,0), //orange
        high = Color.rgb(255,0,0);

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
                buttonColour(traffic,trafficButton);
                soundButton = (ImageButton) stub.findViewById(R.id.button2);
                buttonColour(sound,soundButton);
                airButton = (ImageButton) stub.findViewById(R.id.button3);
                buttonColour(air,airButton);
                buildingButton = (ImageButton) stub.findViewById(R.id.button4);
                buttonColour(build,buildButton);

                if(traffic!=0){
                    trafficButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent(MainActivityWear.this, TrafficActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                if(sound!=0){
                    soundButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent(MainActivityWear.this, SoundActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                if (air != 0) {
                    airButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent(MainActivityWear.this, AirActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                if(build!=0){
                    buildingButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent(MainActivityWear.this, BuildingActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
    
    public static void buttonColour(int status, ImageButton button){
        if(status!=0){
            int back;
            switch(status){
                case 1:
                    back = low;
                    break;
                case 2:
                    back = medium;
                    break;
                case 3:
                    back = high;
                    break;
                default:
                    back = nullStatus;
            }
            button.setBackgroundColor(back);
        }
        else{
            button.setBackgroundColor(nullStatus);
        }
    }
}
