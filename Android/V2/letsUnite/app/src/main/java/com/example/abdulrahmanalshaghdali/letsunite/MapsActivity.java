package com.example.abdulrahmanalshaghdali.letsunite;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;
import java.util.jar.*;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback
{
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 1;
    GoogleApiClient mGoogleApiClient;
    LocationManager lm;
    Location mLastLocation ;

    Button shareLocation ;

    double newLocationLat  ;
    double newLocationLong ;

    double lan;
    double longT ;

    private GoogleMap mMap;

    Marker currentLocation ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if (mGoogleApiClient == null)
        {

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            if(mGoogleApiClient==null)
                Log.d("map","something wrong");

        }

        if(mGoogleApiClient != null)
            mGoogleApiClient.connect();

        shareLocation = (Button) findViewById(R.id.shareLocationBTN);
        shareLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                try {
                    JSONObject postData = new JSONObject();
                    //postData.put("lat", newLocationLat);
                    //postData.put("long", newLocationLong);

                    postData.put("lat",Double.toString(lan));
                    postData.put("long", Double.toString(longT));

                    String str = postData.toString();
                    String re="";


                    BackgroundTasks asyncLoad = new BackgroundTasks();
                    asyncLoad.execute("location/", str, "POST");
                    re = asyncLoad.get(5, TimeUnit.SECONDS);
                    Log.d("HOPE ON ==============>",re);
                    if(re.equals("200")) {
                        Toast.makeText(MapsActivity.this, "Looking for friends for you ;) ",
                                Toast.LENGTH_LONG).show();
                        //Intent myIntent = new Intent(MapsActivity.this, MainActivity.class);
                        //MapsActivity.this.startActivity(myIntent);
                    }else if(re.equals("400")){
                        Toast.makeText(MapsActivity.this, "Try another time",
                                Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(MapsActivity.this, "Ooops something gone wrong",
                                Toast.LENGTH_LONG).show();
                    }

                } catch(JSONException e){
                    e.printStackTrace();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }



    public void showMap()
    {

        //Crete  for the cordeinaters

        LatLng myLocation = new LatLng(lan, longT);// set long and lat to my location
        addNewMarker(myLocation);

        // move the map camera to my location with zoom of 15
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {

                addNewMarker(latLng);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            }
        });
    }
    @Override
    public void onLocationChanged(Location loc) {
        mLastLocation = loc;
        lan = mLastLocation.getLatitude();
        longT = mLastLocation.getLongitude();
        Log.d("lan", Double.toString(lan));
    }

    public void addNewMarker(LatLng latLng)
    {
        // new location coordinates
        newLocationLat = latLng.latitude ;
        newLocationLong = latLng.longitude;

        Toast.makeText(MapsActivity.this, "new location: "+newLocationLat+ " "+ newLocationLong,
                Toast.LENGTH_LONG).show();

        // as it is not null means, rmove old marker and add new one.
        if(currentLocation != null)
        {
            currentLocation.remove();

        }

        currentLocation = mMap.addMarker(new MarkerOptions().
                position(latLng).
                title("MyLocation:x='"+lan+"', y='"+longT+"'")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hostel_0star))//marker icon
        );
        Log.d("lan value is ",Double.toString(lan));
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        if(mGoogleApiClient!=null)
            mGoogleApiClient.connect();
        //boolean permit = (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);

        //boolean permit2 = (ActivityCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);
        //Log.d("Connection",Boolean.toString(permit2));
        // check permessions for location and internet,, and redirect if not permitted
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)){
                //ask the user for permission
            }else{
                //ActivityCompat ac = new ActivityCompat(this).add
                ActivityCompat.requestPermissions(MapsActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSION_ACCESS_COARSE_LOCATION);
            }
            //return;
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Log.d("mLastLocation","out");
        if(mLastLocation != null)
        {

            lan = mLastLocation.getLatitude();
            longT = mLastLocation.getLongitude();
            Log.d("lan",Double.toString(lan));
            showMap();
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
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){

        switch(requestCode){
            case MY_PERMISSION_ACCESS_COARSE_LOCATION:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //permission granted
                }else{
                    //permission denied, cannot switch to next activity
                    Log.d("Permisson","Denied");
                }
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Log.d("Connection","Failed");
    }
    @Override
    protected void onStart()
    {

        super.onStart();
        if(mGoogleApiClient!=null)
            mGoogleApiClient.connect();

    }

    @Override
    protected void onStop()
    {
        if(mGoogleApiClient != null && mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
        super.onStop();


    }
}
