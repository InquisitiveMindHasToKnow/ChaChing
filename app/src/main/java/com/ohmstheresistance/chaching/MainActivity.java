package com.ohmstheresistance.chaching;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ohmstheresistance.chaching.fragments.FragmentNavigation;
import com.ohmstheresistance.chaching.fragments.MainFragment;
import com.ohmstheresistance.chaching.fragments.MapFragment;
import com.ohmstheresistance.chaching.map.GoogleMaps;

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
    public void goToLocationOnMap(String lon, String lat) {

        //GoogleMaps googleMaps = GoogleMaps.getInstance(lon, lat)
        MapFragment mapFragment = MapFragment.getInstance(lon, lat);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, mapFragment)
                .addToBackStack(null)
                .commit();
    }
}

