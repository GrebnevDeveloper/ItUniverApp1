package com.developer.grebnev.ituniverapp1.data.local.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Grebnev on 26.11.2017.
 */
@Table(name = "Employer")
public class EmployerLocal {
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
