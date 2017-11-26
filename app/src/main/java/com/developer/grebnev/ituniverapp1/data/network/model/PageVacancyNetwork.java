package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Grebnev on 03.11.2017.
 */

public class PageVacancyNetwork {
    @SerializedName("items")
    private List<VacancyNetwork> vacanciesList = null;

    public List<VacancyNetwork> getVacanciesList() {
        return vacanciesList;
    }

    public void setVacanciesList(List<VacancyNetwork> vacanciesList) {
        this.vacanciesList = vacanciesList;
    }
}
