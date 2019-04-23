package com.ohmstheresistance.chaching;

public class ChaChingLocation implements Comparable<ChaChingLocation> {

    private String country;
    private String name;
    private Integer _id;

    public ChaChingLocation(String country, String name, Integer _id) {
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

    public Integer get_id() {
        return _id;
    }

    @Override
    public int compareTo(ChaChingLocation o) {
        if (this._id > o._id) {
            return 1;
        } else if (this._id < o._id) {
            return -1;
        } else {
            return 0;
        }
    }


}
