package com.developer.grebnev.ituniverapp1.domain.entity;

/**
 * Created by Grebnev on 07.12.2017.
 */

public class Salary {
    private final Integer to;
    private final Integer from;
    private final String currency;

    public Salary(Integer to, Integer from, String currency) {
        this.to = to;
        this.from = from;
        this.currency = currency;
    }

    public Integer getTo() {
        return to;
    }

    public Integer getFrom() {
        return from;
    }

    public String getCurrency() {
        return currency;
    }

}
