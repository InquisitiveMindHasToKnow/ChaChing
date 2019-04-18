package com.ohmstheresistance.chaching.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ohmstheresistance.chaching.R;
import com.ohmstheresistance.chaching.model.Country;

public class CountryViewHolder extends RecyclerView.ViewHolder {

    private TextView countryName;

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);

        countryName = itemView.findViewById(R.id.country_name_textview);
    }

    public void onBind(final Country country) {

        countryName.setText(country.getName());

    }
}
