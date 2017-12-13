package com.developer.grebnev.ituniverapp1.data.local.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Grebnev on 26.11.2017.
 */
@Table(name = "Salary")
public class SalaryLocal extends Model {
    @Column(name = "to_sal")
    private Integer to;
    @Column(name = "from_sal")
    private Integer from;
    @Column(name = "currency")
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
