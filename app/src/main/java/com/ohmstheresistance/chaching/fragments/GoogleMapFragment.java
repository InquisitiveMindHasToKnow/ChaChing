package com.ohmstheresistance.chaching.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ohmstheresistance.chaching.R;


public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap chaChingMap;
    private MapView chaChingMapView;
    private View googleMapView;

    private static final String LOCATION_LON = "lon";
    private static final String LOCATION_LAT = "lat";
    private static final String LOCATION_CITY = "cityname";
    private static final String LOCATION_COUNTRY = "countryabbreviation";
    private String TAG_FOR_MAP_ICON = "";
    private Context context;

    private String latitude;
    private String longitude;
    private String city;
    private String country;

    private double lat;
    private double lon;

    private ProgressDialog dialog;

    public GoogleMapFragment() {
        // Required empty public constructor
    }

    public static GoogleMapFragment getInstance(String locationLongitude, String locationLatitude, String cityName, String countryAbbreviation) {
        GoogleMapFragment googleMapFragment = new GoogleMapFragment();

        Bundle args = new Bundle();
        args.putString(LOCATION_LON, locationLongitude);
        args.putString(LOCATION_LAT, locationLatitude);
        args.putString(LOCATION_CITY, cityName);
        args.putString(LOCATION_COUNTRY, countryAbbreviation);
        googleMapFragment.setArguments(args);
        return googleMapFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return googleMapView = inflater.inflate(R.layout.fragment_map, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chaChingMapView = googleMapView.findViewById(R.id.googlemaps);

        if (chaChingMapView != null) {
            chaChingMapView.onCreate(null);
            chaChingMapView.onResume();
            chaChingMapView.getMapAsync(this);

            if (getArguments() != null) {
                longitude = getArguments().getString(LOCATION_LON);
                latitude = getArguments().getString(LOCATION_LAT);
                city = getArguments().getString(LOCATION_CITY);
                country = getArguments().getString(LOCATION_COUNTRY);

                TAG_FOR_MAP_ICON = city + ", " + country;

                lat = Double.parseDouble(latitude);
                lon = Double.parseDouble(longitude);
                Log.e("Converted Longitude: ", longitude);


                setDialog();

            }

        }

    }

    public void setDialog() {
        dialog = new ProgressDialog(getContext(), R.style.DialogCustom);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        chaChingMap = googleMap;


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
        }, 4000);

    }
}

