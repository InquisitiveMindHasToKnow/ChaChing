package com.ohmstheresistance.chaching.map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ohmstheresistance.chaching.R;

public class GoogleMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap chaChingMap;
    private FusedLocationProviderClient fusedLocationClient;
    private static final String LOCATION_LON = "lon";
    private static final String LOCATION_LAT = "lat";
    private static final String LOCATION_CITY = "cityname";
    private static final String LOCATION_COUNTRY = "countryabbreviation";

    private Bundle mapBundle;
    private Intent mapIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        mapIntent = getIntent();
        mapBundle = new Bundle();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        chaChingMap = googleMap;


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location != null) {

                chaChingMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
                chaChingMap.setMyLocationEnabled(true);
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    double lat = location.getLatitude();
                                    double lon = location.getLongitude();
                                    chaChingMap.addMarker(new MarkerOptions().position(new LatLng(lon, lat)).title(LOCATION_CITY));
                                }
                            }

                        });
            }
        }


        double lon = Double.parseDouble(LOCATION_LON);
        double lat = Double.parseDouble(LOCATION_LAT);

        Log.d("Omar", "longof onMapReady: " + LOCATION_LON);


        LatLng location = new LatLng(lon, lat);
        chaChingMap.addMarker(new MarkerOptions().position(location).title(LOCATION_CITY));
        chaChingMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        UiSettings uiSettings = chaChingMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
    }

}

