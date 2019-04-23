package com.ohmstheresistance.chaching.network;

import com.google.gson.Gson;
import com.ohmstheresistance.chaching.model.Country;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit retrofitInstance;

    private RetrofitSingleton(){}


    public static Retrofit getRetrofitInstance() {
        if (retrofitInstance != null) {
            return retrofitInstance;
        }

        retrofitInstance = new Retrofit
                .Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitInstance;
    }
}

