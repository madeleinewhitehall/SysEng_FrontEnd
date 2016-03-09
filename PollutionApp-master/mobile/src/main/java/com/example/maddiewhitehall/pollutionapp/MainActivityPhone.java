package com.example.maddiewhitehall.pollutionapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivityPhone extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    GoogleApiClient mGoogleApiClient = null;
    public static final String TAG = "MyDataMap";
    public static final String WEARABLE_DATA_PATH = "/wearable/data/path"; //can be any string that starts with a forward slash
    private static final String TAG_2 = "MainActivity";


    private class FetchDataTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void...params)
        {
            try
            {
                String result = new DataFetcher().getUrlString("https://fierce-peak-41091.herokuapp.com/api");
                JSONArray jsonArray = new JSONArray(result);
                JSONObject object = jsonArray.getJSONObject(1);
                double NO2 = object.getDouble("NO2");
                double CO = object.getDouble("CO");
                //Log.i(TAG, "Fetched contents of URL: " + NO2);
                Log.i(TAG, "NO2: " + NO2 + ", CO: " + CO);
                sendDataMapToDataLayer((float)NO2,(float)CO);
            }
            catch (JSONException je)
            {
                Log.e(TAG, "Failed to parse json", je);
            }
            catch (IOException ioe)
            {
                Log.e(TAG, "Failed to fetch URL: ", ioe);
            }
            return  null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(Wearable.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        mGoogleApiClient = builder.build();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if(mGoogleApiClient != null && mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        sendDataMapToDataLayer(3.0f, 3.0f);
    }

    private DataMap createDataMap(float NO2,float CO)
    {
        DataMap dataMap = new DataMap();
        dataMap.putLong("time", System.currentTimeMillis());
        dataMap.putFloat("CO2", CO);
        dataMap.putFloat("PM2.5", 3.0f);
        dataMap.putFloat("NO2", NO2);
        return dataMap;
    }


    public void sendDataMapToDataLayer(float NO2, float CO)
    {
        if(mGoogleApiClient.isConnected())
        {
            DataMap dataMap = createDataMap(NO2,CO);
            new SendDataMapToDataLayer(WEARABLE_DATA_PATH,dataMap).start();
        }
        else
        {
            Log.v(TAG, "Connection is closed");
        }

    }

    public void sendDataMapOnClick(View view)
    {
        new FetchDataTask().execute();
    }



    public class SendDataMapToDataLayer extends  Thread
    {
        String mPath;
        DataMap mDataMap;

        public SendDataMapToDataLayer(String path, DataMap dataMap)
        {
            this.mPath = path;
            this.mDataMap = dataMap;
        }

        @Override
        public void run()
        {
            PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(WEARABLE_DATA_PATH);
            putDataMapRequest.getDataMap().putAll(mDataMap); //wrap the DataMap object inside the PutDataMapRequest
            PutDataRequest putDataRequest = putDataMapRequest.asPutDataRequest();

            DataApi.DataItemResult dataItemResult = Wearable.DataApi.putDataItem(mGoogleApiClient, putDataRequest).await(); // await() blocks the thread until we got the result
            if(dataItemResult.getStatus().isSuccess())
            {
                Log.v(TAG,"DataItem successfully sent");
            }
            else
            {
                Log.v(TAG,"error while sending DataItem");
            }

        }

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}