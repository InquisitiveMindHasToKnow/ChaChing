package com.ohmstheresistance.chaching.model;

public class Country {

    private String country;
    private String name;
    private int _id;

    public Country(String country, String name, int _id) {
        this.country = country;
        this.name = name;
        this._id = _id;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int get_id() {
        return _id;
    }



    public class LocationCoordinates {

        private Double lon;
        private Double lat;

        public Double getLon() {
            return lon;
        }

        public Double getLat() {
            return lat;
        }

    }
}
