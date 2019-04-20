package com.ohmstheresistance.chaching;

import android.content.Intent;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ohmstheresistance.chaching.fragments.FragmentNavigation;
import com.ohmstheresistance.chaching.fragments.MainFragment;
import com.ohmstheresistance.chaching.map.GoogleMaps;

public class MainActivity extends AppCompatActivity implements FragmentNavigation {

    private static final String LOCATION_LON = "lon";
    private static final String LOCATION_LAT = "lat";
    private static final String LOCATION_CITY = "cityname";
    private static final String LOCATION_COUNTRY = "countryabbreviation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, mainFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void goToLocationOnMap(String lon, String lat, String city, String country) {
        Intent goToMapIntent = new Intent(MainActivity.this, GoogleMaps.class);
        goToMapIntent.putExtra(LOCATION_LON, lon);
        goToMapIntent.putExtra(LOCATION_LAT, lat);
        goToMapIntent.putExtra(LOCATION_CITY, city);
        goToMapIntent.putExtra(LOCATION_COUNTRY, country);
        startActivity(goToMapIntent);
    }
}

