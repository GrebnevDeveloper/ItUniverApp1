package com.developer.grebnev.ituniverapp1.domain.entity;

/**
 * Created by Grebnev on 07.12.2017.
 */

public class Address {
    private final String building;
    private final String city;
    private final String street;

    public Address(String building, String city, String street) {
        this.building = building;
        this.city = city;
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

}
