package com.nmt.model;

public class Country {
    protected int countryID;
    protected String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(int countryID, String name) {
        this.countryID = countryID;
        this.name = name;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
