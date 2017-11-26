package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 02.11.2017.
 */
public class AddressNetwork {
    @SerializedName("building")
    private String building;
    @SerializedName("city")
    private String city;
    @SerializedName("street")
    private String street;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
