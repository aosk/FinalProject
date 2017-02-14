package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation ;

    Button shareLocation ;

    double newLocationLat  ;
    double newLocationLong ;

    double lan ;
    double longT ;

    private GoogleMap mMap;

    Marker currentLocation ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



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

        }

        shareLocation = (Button) findViewById(R.id.shareLocationBTN);
        shareLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Toast.makeText(MapsActivity.this, "new location: "+newLocationLat+ " "+ newLocationLong,
                        Toast.LENGTH_LONG).show();

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
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {

        mMap = googleMap;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {

        // check permessions for location and internet,, and redirect if not permitted
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if(mLastLocation != null)
        {

            lan = mLastLocation.getLatitude();
            longT = mLastLocation.getLongitude();

            showMap();

        }

    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }

    protected void onStart()
    {

        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop()
    {

        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
