package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 02.11.2017.
 */
public class VacancyNetwork {
    @SerializedName("snippet")
    @Expose
    private SnippetNetwork snippet;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("address")
    @Expose
    private AddressNetwork address;
    @SerializedName("id")
    @Expose
    private String idVacancy;

    public VacancyNetwork() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AddressNetwork getAddress() {
        return address;
    }

    public void setAddress(AddressNetwork address) {
        this.address = address;
    }

    public String getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(String id) {
        this.idVacancy = id;
    }

    public SnippetNetwork getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetNetwork snippet) {
        this.snippet = snippet;
    }
}
