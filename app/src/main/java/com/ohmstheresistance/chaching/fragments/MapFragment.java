package com.ohmstheresistance.chaching.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohmstheresistance.chaching.R;

public class MapFragment extends Fragment {

    private View rootView;

    private static final String LOCATION_LON = "lon";
    private static final String LOCATION_LAT = "lat";

    private String lon;
    private String lat;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment getInstance(String lon, String lat) {
        MapFragment fragment = new MapFragment();


        Bundle args = new Bundle();
        args.putString(LOCATION_LON, lon);
        args.putString(LOCATION_LAT, lat);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        return rootView;
    }

}
