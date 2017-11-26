package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 26.11.2017.
 */

public class EmployerNetwork {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
