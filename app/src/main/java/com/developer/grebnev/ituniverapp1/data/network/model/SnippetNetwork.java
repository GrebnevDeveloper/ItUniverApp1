package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 02.11.2017.
 */
public class SnippetNetwork {
    @SerializedName("requirement")
    @Expose
    private String requirement;
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
