package com.ohmstheresistance.chaching.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohmstheresistance.chaching.R;
import com.ohmstheresistance.chaching.model.Country;
import com.ohmstheresistance.chaching.network.CountryService;
import com.ohmstheresistance.chaching.network.RetrofitSingleton;
import com.ohmstheresistance.chaching.recyclerview.CountryAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainFragment extends Fragment implements SearchView.OnQueryTextListener {

    private View rootView;
    private static final String TAG = "CountryJSON.TAG";
    private RecyclerView countryRecyclerView;
    private SearchView citySearchView;
    private CountryAdapter countryAdapter;
    private Context context;
    private List<Country> countryList;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        countryRecyclerView = rootView.findViewById(R.id.country_recycler_view);
        citySearchView = rootView.findViewById(R.id.city_search_view);
        countryList = new ArrayList<>();

        Retrofit countryRetrofit = RetrofitSingleton.getRetrofitInstance();
        CountryService countryService = countryRetrofit.create(CountryService.class);
        countryService.getCountries().enqueue(new Callback<List<Country>>() {

            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                countryList = response.body();
                Log.d(TAG, "Country Retrofit Call Works: " + response.body().get(0).getCountry());
                Log.e(TAG, "the location works, Longitude: " + countryList.get(0).getCoord().getLon());
                Log.e(TAG, "the location works, Latitude: " + countryList.get(0).getCoord().getLat());

                countryAdapter = new CountryAdapter(response.body());
                countryRecyclerView.setAdapter(countryAdapter);
                countryRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                citySearchView.setOnQueryTextListener(MainFragment.this);

                sortAllphabetically();


            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d(TAG, "Country Retrofit Call Failed: " + t.getMessage());

            }

        });
        return rootView;
    }

    private void sortAllphabetically(){
        Collections.sort(countryList, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<Country> newCountryList = new ArrayList<>();
        for (Country country : countryList) {

            if (country.getName().toLowerCase().contains(s.toLowerCase())) {
                newCountryList.add(country);
            }
        }
        countryAdapter.setData(newCountryList);
        return false;
    }

}
