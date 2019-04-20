package com.ohmstheresistance.chaching.model;

import java.util.List;

public class Country {

    private String country;
    private String name;
    private Integer _id;
    private LocationCoordinates coord;



    public Country(String country, String name, Integer _id, LocationCoordinates coord) {
        this.country = country;
        this.name = name;
        this._id = _id;
        this.coord = coord;

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

    public LocationCoordinates getCoord() {
        return coord;
    }

    public static class LocationCoordinates {

        private String lat;
        private String lon;

        public LocationCoordinates(String lat, String lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public String getLon() {
            return lon;
        }

        public String getLat() {
            return lat;
        }

    }

//    public static class LocationAPI {
//        private static List<LocationCoordinates> coord;
//
//
//        public LocationAPI(List<LocationCoordinates> coord) {
//            this.coord = coord;
//
//        }
//
//        public static List<LocationCoordinates> getCoords() {
//            return coord;
//        }
//
//    }
}
