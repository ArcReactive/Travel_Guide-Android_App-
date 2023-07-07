package com.nuwansudusinghe.appliccation01;

public class City {
    private String country;
    private String name;

    public City(String country, String name) {
        this.country = country;
        this.name =  name;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }
}
