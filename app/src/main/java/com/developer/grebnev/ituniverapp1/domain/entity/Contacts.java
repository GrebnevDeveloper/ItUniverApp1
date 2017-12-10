package com.developer.grebnev.ituniverapp1.domain.entity;

/**
 * Created by Grebnev on 07.12.2017.
 */

public class Contacts {
    private final String email;
    private final Phone phone;

    public Contacts(String email, Phone phone) {
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public Phone getPhone() {
        return phone;
    }
}
