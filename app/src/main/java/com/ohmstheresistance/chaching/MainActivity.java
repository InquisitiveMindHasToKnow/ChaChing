package com.ohmstheresistance.chaching;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ohmstheresistance.chaching.fragments.FragmentNavigation;
import com.ohmstheresistance.chaching.fragments.MainFragment;
import com.ohmstheresistance.chaching.fragments.GoogleMapFragment;

public class MainActivity extends AppCompatActivity implements FragmentNavigation {

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
        GoogleMapFragment googleMapFragment = GoogleMapFragment.getInstance(lon, lat, city, country);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, googleMapFragment)
                .addToBackStack(null)
                .commit();
    }
}

