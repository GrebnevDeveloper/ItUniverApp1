package com.developer.grebnev.ituniverapp1.data.local.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Grebnev on 02.11.2017.
 */
@Table(name = "Address")
public class AddressLocal extends Model {
    @Column(name = "building")
    private String building;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
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
