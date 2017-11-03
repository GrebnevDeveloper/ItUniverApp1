package com.developer.grebnev.ituniverapp1.data.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 02.11.2017.
 */
@Table(name = "Snippet")
public class Snippet extends Model{
    @Column(name = "requirement")
    @SerializedName("requirement")
    @Expose
    private String requirement;
    @Column(name = "responsibility")
    @SerializedName("responsibility")
    @Expose
    private String responsibility;

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }
}
