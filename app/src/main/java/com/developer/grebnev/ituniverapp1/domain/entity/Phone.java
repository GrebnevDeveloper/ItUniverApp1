package com.developer.grebnev.ituniverapp1.domain.entity;

/**
 * Created by Grebnev on 07.12.2017.
 */

public class Phone {
    private final String country;
    private final String city;
    private final String number;

    public Phone(String country, String city, String number) {
        this.country = country;
        this.city = city;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

}
