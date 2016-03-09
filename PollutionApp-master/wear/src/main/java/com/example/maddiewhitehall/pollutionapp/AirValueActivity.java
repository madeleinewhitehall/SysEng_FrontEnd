package com.example.maddiewhitehall.pollutionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.Color;

public class AirValueActivity extends Activity {

    private TextView mTextView;
    private ImageButton buttonBack, cloudView;
    private Intent intentBack, intent;
    RelativeLayout a, b, c, d, e, f, g, h, i, j;
    RelativeLayout[][] scale = new RelativeLayout[3][5];
    int levelScale = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_value2);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);

                buttonBack = (ImageButton) stub.findViewById(R.id.backButton);


                scale[0][0] = (RelativeLayout) stub.findViewById(R.id.Grid1a);
                scale[0][1] = (RelativeLayout) stub.findViewById(R.id.Grid1b);
                scale[0][2] = (RelativeLayout) stub.findViewById(R.id.Grid1c);
                scale[0][3] = (RelativeLayout) stub.findViewById(R.id.Grid1d);
                scale[0][4] = (RelativeLayout) stub.findViewById(R.id.Grid1e);
                scale[1][0] = (RelativeLayout) stub.findViewById(R.id.Grid2a);
                scale[1][1] = (RelativeLayout) stub.findViewById(R.id.Grid2b);
                scale[1][2] = (RelativeLayout) stub.findViewById(R.id.Grid2c);
                scale[1][3] = (RelativeLayout) stub.findViewById(R.id.Grid2d);
                scale[1][4] = (RelativeLayout) stub.findViewById(R.id.Grid2e);
                scale[2][0] = (RelativeLayout) stub.findViewById(R.id.Grid3a);
                scale[2][1] = (RelativeLayout) stub.findViewById(R.id.Grid3b);
                scale[2][2] = (RelativeLayout) stub.findViewById(R.id.Grid3c);
                scale[2][3] = (RelativeLayout) stub.findViewById(R.id.Grid3d);
                scale[2][4] = (RelativeLayout) stub.findViewById(R.id.Grid3e);


                for (int n = 0; n < 3; n++) {
                    for (int k = 0; k < levelScale; k++) {
                        scale[n][k].setBackgroundColor(Color.rgb(0, 0, 0));
                    }
                    levelScale++;
                }

                cloudView = (ImageButton) stub.findViewById(R.id.cloudButton);
                cloudView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(AirValueActivity.this, AirActivity.class);
                        startActivity(intent);
                        //finish();
                    }

                });
            }
        });

    }
}

   /* public void createScale(int scaleNum, RelativeLayout scale[int n][]) {


        for (int k = 0; k < scaleNum; k++) {
            scale[n][k].setBackgroundColor(Color.rgb(255, 128, 0));
        }


    };*/




//need a method that will pass in the scale number and the array of IDs and will create the RelativeLayout thing...

