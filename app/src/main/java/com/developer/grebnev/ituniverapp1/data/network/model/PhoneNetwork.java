package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 26.11.2017.
 */

public class PhoneNetwork {
    @SerializedName("country")
    private String country;
    @SerializedName("city")
    private String city;
    @SerializedName("number")
    private String number;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
