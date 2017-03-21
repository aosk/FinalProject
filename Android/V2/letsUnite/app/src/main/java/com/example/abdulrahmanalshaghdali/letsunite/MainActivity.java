package com.example.abdulrahmanalshaghdali.letsunite;

import android.*;
import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private Button boerdBTN;
    private Button friendsBTN;
    private Button eventBTN;
    private Button newsBTN;
    private Switch switchBT;
    private boolean switchCheck;
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 1;

    Location mLastLocation;
    GoogleApiClient mGoogleApiClient;
    LocationManager lm;

    double lan;
    double longT;
    double newLocationLat;
    double newLocationLong;

    String re;
    private TabHost th;

    TextView tx;
    String text;
    ActionBar actionBar;
    private Handler handler;
    private String fname = "mydata";

    String user_id;
    String group_id;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handler = new Handler();
        //handler.postDelayed(runnable,1000);


        // Create an instance of GoogleAPIClient.

        if (mGoogleApiClient == null) {

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            if (mGoogleApiClient == null)
                Log.d("map", "something wrong");

        }

        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();


        try {
            FileInputStream fin = openFileInput(fname);
            InputStreamReader myReader = new InputStreamReader(fin);
            BufferedReader myBR = new BufferedReader(myReader);
            user_id = myBR.readLine();
            group_id = myBR.readLine();
            Log.d("user_id =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>", user_id);
            Log.d("group_id =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>", group_id);
            myBR.close();
            myReader.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        boerdBTN = (Button) findViewById(R.id.boredBTN);
        boerdBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });


        eventBTN = (Button) findViewById(R.id.eventsBTN);
        eventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, " It is events ",
                //        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, Events_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("events",re);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        newsBTN = (Button) findViewById(R.id.newsBT);
        newsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, " It is news ",
                        Toast.LENGTH_LONG).show();


                Intent intent = new Intent(MainActivity.this, GetActivity.class);

                startActivity(intent);

            }
        });


        Toast.makeText(MainActivity.this, user_id,
                Toast.LENGTH_LONG).show();

        switchBT = (Switch) findViewById(R.id.switchBT);
        switchBT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                /*
                * if the user allow to share location:
                * Im trying to detect the location here and store latitude and longitude
                * And pass all the require information every 5 mins to the server as put request
                * the respond should contain events that should be showed in events page.
                *
                * */
                switchCheck = isChecked;
                if (isChecked) {


                    lan = mLastLocation.getLatitude();
                    longT = mLastLocation.getLongitude();
                    Log.d("lan",Double.toString(lan));

                    Toast.makeText(MainActivity.this, "new location: "+lan+ " "+ longT,
                            Toast.LENGTH_LONG).show();

                    //handler = new Handler();
                    //handler.postDelayed(runnable,1000);

                    //Put here
                    BackgroundTasks asyncTask = new BackgroundTasks();
                    BackgroundTasks asyncTask2 = new BackgroundTasks();
                    BackgroundTasks asyncTask3 = new BackgroundTasks();
                    BackgroundTasks asyncTask4 = new BackgroundTasks();
                    BackgroundTasks asyncTask5 = new BackgroundTasks();
                    //token place should be a token that identify the user himself

                    try {
                        asyncTask.execute("events/",user_id,"PUT", group_id,Double.toString(lan),Double.toString(longT));
                        re = asyncTask.get(5, TimeUnit.SECONDS);
                        Log.d("Event data",re);
                        //wait(100);
                        asyncTask2.execute("events/","b","PUT", group_id,Double.toString(lan+0.000001),Double.toString(longT+0.000002));
                        re = asyncTask2.get(5, TimeUnit.SECONDS);
                        Log.d("Event data",re);
                        //wait(1000);
                        asyncTask3.execute("events/","c","PUT", group_id,Double.toString(lan-0.000001),Double.toString(longT-0.000002));
                        re = asyncTask3.get(5, TimeUnit.SECONDS);
                        Log.d("Event data",re);
                        //wait(1000);
                        /*
                        asyncTask4.execute("events/","d","PUT", group_id,Double.toString(lan-0.000010),Double.toString(longT));
                        re = asyncTask4.get(5, TimeUnit.SECONDS);
                        Log.d("Event data",re);
                        //wait(1000);
                        asyncTask5.execute("events/","e","PUT", group_id,Double.toString(lan),Double.toString(longT+0.000010));
                        re = asyncTask5.get(5, TimeUnit.SECONDS);
                        Log.d("Event data",re);
                        */
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Log.v("========== = = = good to go = = = = ================", "" + isChecked);


                } else {
                    Log.v("========== = = = Stop Stop Stop..!! = = = = ================", "" + isChecked);

                    Toast.makeText(MainActivity.this, "YOU CAN NOT SEE EVENTS ",
                            Toast.LENGTH_LONG).show();
                }

            }
        });


    }//End OnCreate



    // Adding par to stop Runabble when switch is off ================== 1
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

                //Put here
                BackgroundTasks asyncTask = new BackgroundTasks();

                //token place should be a token that identify the user himself
                asyncTask.execute("events/",user_id,"PUT", group_id,Double.toString(lan),Double.toString(longT));
                try {
                    re = asyncTask.get(5, TimeUnit.SECONDS);
                    Log.d("Event data",re);

                }catch(Exception e){
                    e.printStackTrace();
                }
            if(switchCheck)
                handler.postDelayed(this,5000);
        }
    };



    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COARSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                } else {
                    //permission denied, cannot switch to next activity
                    Log.d("Permisson", "Denied");
                }
                return;
            }
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(mGoogleApiClient!=null)
            mGoogleApiClient.connect();
        //boolean permit = (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);

        //boolean permit2 = (ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);
        //Log.d("Connection",Boolean.toString(permit2));
        // check permessions for location and internet,, and redirect if not permitted
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)){


            }else{
                //ActivityCompat ac = new ActivityCompat(this).add
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSION_ACCESS_COARSE_LOCATION);
            }
            //return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Log.d("mLastLocation","out");
        if(mLastLocation != null)
        {

            //lan = mLastLocation.getLatitude();
            //longT = mLastLocation.getLongitude();
            //Log.d("lan",Double.toString(lan));
        }else{
            Log.d("mLastLocation","does not exist");
            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setInterval(5);
            mLocationRequest.setFastestInterval(10);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            PendingResult p = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,this);
            //LocationServices.FusedLocationApi.

            //mLastLocation= LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            //lan = mLastLocation.getLatitude();
            //longT = mLastLocation.getLongitude();
            //Log.d("lan",p.isCanceled());

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
}