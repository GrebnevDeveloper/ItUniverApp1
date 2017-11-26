package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 26.11.2017.
 */

public class SalaryNetwork {
    @SerializedName("to")
    private Integer to;
    @SerializedName("from")
    private Integer from;
    @SerializedName("currency")
    private String currency;

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
