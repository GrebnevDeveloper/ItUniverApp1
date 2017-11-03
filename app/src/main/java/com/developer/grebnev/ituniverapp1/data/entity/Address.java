package com.developer.grebnev.ituniverapp1.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 02.11.2017.
 */
@Table(name = "Address")
public class Address extends Model {
    @Column(name = "building")
    @SerializedName("building")
    @Expose
    private String building;
    @Column(name = "city")
    @SerializedName("city")
    @Expose
    private String city;
    @Column(name = "street")
    @SerializedName("street")
    @Expose
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
