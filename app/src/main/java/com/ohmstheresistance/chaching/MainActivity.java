package com.ohmstheresistance.chaching;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ohmstheresistance.chaching.model.Country;
import com.ohmstheresistance.chaching.network.CountryService;
import com.ohmstheresistance.chaching.network.RetrofitSingleton;
import com.ohmstheresistance.chaching.recyclerview.CountryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CountryJSON.TAG";
    private RecyclerView countryRecyclerView;
    private List<Country> countries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryRecyclerView = findViewById(R.id.country_recycler_view);

        Retrofit countryRetrofit = RetrofitSingleton.getRetrofitInstance();
        CountryService countryService = countryRetrofit.create(CountryService.class);
        countryService.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            @NonNull
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                Log.d(TAG, "Country Retrofit Call Works: " + response.body().get(0).getCountry());
                CountryAdapter adapter = new CountryAdapter(response.body());
                countryRecyclerView.setAdapter(adapter);
                countryRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d(TAG, "Country Retrofit Call Failed: " + t.getMessage());

            }

        });

    }
}
