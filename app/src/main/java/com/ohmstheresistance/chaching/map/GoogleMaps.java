package com.ohmstheresistance.chaching.map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ohmstheresistance.chaching.R;
import com.ohmstheresistance.chaching.fragments.MapFragment;

public class GoogleMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String LOCATION_LON = "lon";
    private static final String LOCATION_LAT = "lat";

    private String lon;
    private String lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public static GoogleMaps getInstance(String lon, String lat) {
        GoogleMaps googleMaps = new GoogleMaps();


        Bundle args = new Bundle();
        args.putString(LOCATION_LON, lon);
        args.putString(LOCATION_LAT, lat);
        //  googleMaps.setArguments(args);
        return googleMaps;
    }

}
