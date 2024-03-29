package com.ohmstheresistance.chaching.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohmstheresistance.chaching.R;
import com.ohmstheresistance.chaching.model.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private List<Country> countryList;

    public CountryAdapter(List<Country>countryList){
        this.countryList = countryList;
    }
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_item_view, viewGroup, false);
        return new CountryViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {

        Country country = countryList.get(i);
        countryViewHolder.onBind(country);
    }
    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void setData(List<Country> newCountryList) {
        this.countryList = newCountryList;
        notifyDataSetChanged();
    }
}
