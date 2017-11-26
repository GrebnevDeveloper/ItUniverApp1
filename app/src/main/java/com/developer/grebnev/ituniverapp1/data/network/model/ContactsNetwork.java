package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Grebnev on 26.11.2017.
 */

public class ContactsNetwork {
    @SerializedName("email")
    private String email;
    @SerializedName("phones")
    private List<PhoneNetwork> phones = null;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneNetwork> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNetwork> phones) {
        this.phones = phones;
    }
}
