package com.developer.grebnev.ituniverapp1.mvp.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Grebnev on 18.10.2017.
 */
@Table(name = "Vacancy", id = "_id")
public class Vacancy extends Model {
    @Column(name = "name")
    private String name;
    @Column(name = "addressVacancy")
    private String addressVacancy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressVacancy() {
        return addressVacancy;
    }

    public void setAddressVacancy(String addressVacancy) {
        this.addressVacancy = addressVacancy;
    }
}
