package com.ohmstheresistance.chaching.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ohmstheresistance.chaching.R;
import com.ohmstheresistance.chaching.model.Country;
import com.ohmstheresistance.chaching.network.CountryService;
import com.ohmstheresistance.chaching.network.RetrofitSingleton;
import com.ohmstheresistance.chaching.recyclerview.CountryAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final String TAG = "CountryJSON.TAG";


    private RecyclerView countryRecyclerView;
    private SearchView citySearchView;
    private CountryAdapter countryAdapter;
    private Context context;
    private List<Country> countryList;
    private LinearLayoutManager linearLayoutManager;
    Parcelable currentState;


    private RadioGroup radioGroup;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        countryRecyclerView = rootView.findViewById(R.id.country_recycler_view);
        citySearchView = rootView.findViewById(R.id.city_search_view);
        radioGroup = rootView.findViewById(R.id.radiogroup);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        countryList = new ArrayList<>();

        Retrofit countryRetrofit = RetrofitSingleton.getRetrofitInstance();
        CountryService countryService = countryRetrofit.create(CountryService.class);
        countryService.getCountries().enqueue(new Callback<List<Country>>() {

            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                countryList = response.body();

                if (countryList == null) {
                    Toast.makeText(getContext(), "Unable To Display Empty List", Toast.LENGTH_LONG).show();
                }

                countryAdapter = new CountryAdapter(response.body());
                countryRecyclerView.setAdapter(countryAdapter);
                linearLayoutManager = new LinearLayoutManager(context);
                countryRecyclerView.setLayoutManager(linearLayoutManager);
                citySearchView.setOnQueryTextListener(MainFragment.this);
                citySearchView.setIconified(false);
                citySearchView.clearFocus();


                sortAlphabetically();

                onPause();
                onResume();

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

                Toast.makeText(getContext(), "Country Retrofit Call Failed", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Country Retrofit Call Failed: " + t.getMessage());
            }

        });

    }

    private void resumeFromLasPosition() {
        countryRecyclerView.setAdapter(countryAdapter);
        linearLayoutManager.onRestoreInstanceState(currentState);
    }

    @Override
    public void onPause() {
        super.onPause();
        currentState = linearLayoutManager.onSaveInstanceState();
    }

    private void sortAlphabetically() {
        Collections.sort(countryList, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        countryAdapter.notifyDataSetChanged();
        resumeFromLasPosition();
    }

    private void sortCountryAlphabetically() {
        Collections.sort(countryList, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getCountry().compareTo(o2.getCountry());
            }
        });
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(final String s) {

        int id = radioGroup.getCheckedRadioButtonId();
        switch (id) {

            case R.id.search_by_city_radio_button:

                sortAlphabetically();

                List<Country> newCityList = new ArrayList<>();
                for (Country city : countryList) {

                    if (city.getName().toLowerCase().startsWith(s.toLowerCase())) {
                        newCityList.add(city);
                    }
                }
                countryAdapter.setData(newCityList);
                break;

            case R.id.search_by_country_radio_button:

                sortCountryAlphabetically();

                List<Country> newCountryList = new ArrayList<>();
                for (Country country : countryList) {
                    if (country.getCountry().toLowerCase().startsWith(s.toLowerCase())) {
                        newCountryList.add(country);
                    }
                }
                countryAdapter.setData(newCountryList);
                break;

            default:
                Toast.makeText(context, "Error Filtering", Toast.LENGTH_LONG).show();

                break;
        }

        return false;
    }

}




