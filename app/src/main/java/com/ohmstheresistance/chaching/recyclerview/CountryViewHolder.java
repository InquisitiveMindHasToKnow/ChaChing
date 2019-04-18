package com.ohmstheresistance.chaching.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ohmstheresistance.chaching.R;
import com.ohmstheresistance.chaching.fragments.FragmentNavigation;
import com.ohmstheresistance.chaching.fragments.MapFragment;
import com.ohmstheresistance.chaching.model.Country;

public class CountryViewHolder extends RecyclerView.ViewHolder {

    private TextView countryName;
    private TextView cityName;
    private FragmentNavigation fragmentNavigation;
    private static final String LOCATION_LAT = "lat";
    private static final String LOCATION_LON = "lon";
    private static final String TAG = "CountryJSON.TAG";

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);

        countryName = itemView.findViewById(R.id.country_name_textview);
        cityName = itemView.findViewById(R.id.city_name_textview);
    }

    public void onBind(final Country country) {

        countryName.setText(country.getCountry());
        cityName.setText(country.getName() + ",");

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapIntent = new Intent(itemView.getContext(), MapFragment.class);
                Bundle mapBundle = new Bundle();



                mapBundle.putString(LOCATION_LAT, country.getCoord().getLat());
                mapBundle.putString(LOCATION_LON, country.getCoord().getLon());

                Log.d(TAG,  "the location works, too: " + country.getCoord().getLat());
                Log.d(TAG,  "the location works, too: " + country.getCoord().getLon());

                mapIntent.putExtras(mapBundle);

                fragmentNavigation = (FragmentNavigation) v.getContext();
                fragmentNavigation.goToLocationOnMap(country.getCoord().getLat(), country.getCoord().getLon());
            }
        });

    }
}
