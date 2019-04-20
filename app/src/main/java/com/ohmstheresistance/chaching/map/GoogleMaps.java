package com.ohmstheresistance.chaching.map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
    private String TAG_FOR_MAP_ICON = "";

    private String latitude;
    private String longitude;
    private String city;
    private String country;

    private double lat;
    private double lon;

    private Intent mapIntent;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        mapIntent = getIntent();

        latitude = mapIntent.getStringExtra(LOCATION_LAT);
        longitude = mapIntent.getStringExtra(LOCATION_LON);
        city = mapIntent.getStringExtra(LOCATION_CITY);
        country = mapIntent.getStringExtra(LOCATION_COUNTRY);

        TAG_FOR_MAP_ICON = city + ", " + country;

        lat = Double.parseDouble(latitude);
        lon = Double.parseDouble(longitude);
        Log.e("Converted Longitude: ", longitude);


        setDialog();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    public void setDialog() {
        dialog = new ProgressDialog(this, R.style.DialogCustom);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        chaChingMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);

        } else {

            chaChingMap.setMyLocationEnabled(true);
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location targetLocation) {
                            if (targetLocation != null) {

                                LatLng latLng = new LatLng(lat, lon);
                                chaChingMap.addMarker(new MarkerOptions().position(latLng).title(TAG_FOR_MAP_ICON).icon(BitmapDescriptorFactory.fromResource(R.mipmap.atmformap)));

                                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 8);
                                chaChingMap.animateCamera(cameraUpdate);
                                UiSettings uiSettings = chaChingMap.getUiSettings();
                                uiSettings.setZoomControlsEnabled(true);
                                uiSettings.setMyLocationButtonEnabled(true);

                                Handler progressDialogHandler = new Handler();
                                progressDialogHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                    }
                                }, 6000);

                            }
                        }
                    });

        }

    }

}


