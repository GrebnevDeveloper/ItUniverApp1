package com.developer.grebnev.ituniverapp1.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 02.11.2017.
 */
@Table(name = "Vacancy")
public class Vacancy extends Model {
    @Column(name = "snippet")
    @SerializedName("snippet")
    @Expose
    private Snippet snippet;
    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Column(name = "created_at")
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @Column(name = "address")
    @SerializedName("address")
    @Expose
    private Address address;
    @Column(name = "id_vacancy")
    @SerializedName("id")
    @Expose
    private String idVacancy;

    public Vacancy() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(String id) {
        this.idVacancy = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
