package com.ohmstheresistance.chaching.network;

import com.ohmstheresistance.chaching.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {


    @GET("joinpursuit/Pursuit-Core-Android-Unit6-CTA-Bank-Locator/master/location.json")
    Call<List<Country>>getCountries();
}
