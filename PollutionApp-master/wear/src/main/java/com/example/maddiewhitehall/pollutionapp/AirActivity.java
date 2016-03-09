package com.example.maddiewhitehall.pollutionapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ScrollView;
import android.graphics.Color;

public class AirActivity extends Activity {

    private TextView mTextView,a,b,c,d,e;
    private TextView view[]= {a,b,c,d,e};
    private ImageButton buttonBackOne, buttonBackTwo, cloudView;
    private Intent intentBack, intentValue, intentAir;
    int airRun = 0;
    Float incomingAir[]={3.12f,0.23522f,3.44f,2.3f,0.6f};
    CharSequence airValues[]={"","","","",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                buttonBackOne = (ImageButton) stub.findViewById(R.id.backButton);


                for(int j=0;j<5;j++){
                    airValues[j]=Float.toString(incomingAir[j]);
                }

                view[0] = (TextView) stub.findViewById(R.id.text1);
                view[1] = (TextView) stub.findViewById(R.id.text2);
                view[2] = (TextView) stub.findViewById(R.id.text3);
                view[3] = (TextView) stub.findViewById(R.id.text4);
                view[4] = (TextView) stub.findViewById(R.id.text5);

                String[] dataMap = getIntent().getStringArrayExtra("dataMap");
                int c = 0;
                if(dataMap != null)
                {

                    for(String s : dataMap)
                    {
                       view[c].setText(s);
                        c++;
                    }

                }
                else
                {
                    for(int i=0;i<5;i++) {
                        view[i].setText(airValues[i]);
                    }
                }


                cloudView = (ImageButton) stub.findViewById(R.id.cloudButton);
                cloudView.setOnClickListener(new View.OnClickListener() {
                    @Override

                    /*public void onClick (View v) {
                       if(airRun==0){
                           //intentValue = new Intent(AirActivity.this, AirValueActivity.class);
                           setContentView(R.layout.activity_air_value2);
                           airRun=1;
                       }
                       else{
                           //intentAir = new Intent(AirActivity.this,)
                           setContentView(R.layout.activity_air);
                           airRun=0;
                       }
                    }*/


                    public void onClick (View v) {
                        intentValue = new Intent(AirActivity.this, AirValueActivity.class);
                        startActivity(intentValue);
                        //finish();
                    }
                });
            }
        });
    }
}
