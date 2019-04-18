package com.ohmstheresistance.chaching.model;

import android.support.annotation.NonNull;

import java.util.List;

public class CountryAPI {
    private List<Country> countries;

    @NonNull
    public List<Country> getCountries(){
        return countries;
    }
}
