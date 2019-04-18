package com.ohmstheresistance.chaching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.ohmstheresistance.chaching.model.Country;
import com.ohmstheresistance.chaching.network.CountryService;
import com.ohmstheresistance.chaching.network.RetrofitSingleton;
import com.ohmstheresistance.chaching.recyclerview.CountryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "CountryJSON.TAG";
    private RecyclerView countryRecyclerView;
    private SearchView citySearchView;
    private CountryAdapter countryAdapter;
    private List<Country> countryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryRecyclerView = findViewById(R.id.country_recycler_view);
        citySearchView = findViewById(R.id.city_search_view);

        Retrofit countryRetrofit = RetrofitSingleton.getRetrofitInstance();
        CountryService countryService = countryRetrofit.create(CountryService.class);
        countryService.getCountries().enqueue(new Callback<List<Country>>() {

            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                Log.d(TAG, "Country Retrofit Call Works: " + response.body().get(7).getCountry());
                countryAdapter = new CountryAdapter(response.body());
                countryRecyclerView.setAdapter(countryAdapter);
                countryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                citySearchView.setOnQueryTextListener(MainActivity.this);

                countryList = response.body();

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d(TAG, "Country Retrofit Call Failed: " + t.getMessage());

            }

        });

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
