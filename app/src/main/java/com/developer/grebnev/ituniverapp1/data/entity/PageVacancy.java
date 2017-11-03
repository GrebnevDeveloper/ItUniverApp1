package com.developer.grebnev.ituniverapp1.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Grebnev on 03.11.2017.
 */

public class PageVacancy {
    @SerializedName("items")
    @Expose
    private List<Vacancy> vacanciesList = null;

    public List<Vacancy> getVacanciesList() {
        return vacanciesList;
    }

    public void setVacanciesList(List<Vacancy> vacanciesList) {
        this.vacanciesList = vacanciesList;
    }
}
