package com.developer.grebnev.ituniverapp1.data.entity;

import com.developer.grebnev.ituniverapp1.data.local.model.AddressLocal;
import com.developer.grebnev.ituniverapp1.data.local.model.SnippetLocal;
import com.developer.grebnev.ituniverapp1.data.network.model.AddressNetwork;
import com.developer.grebnev.ituniverapp1.data.network.model.SnippetNetwork;

/**
 * Created by Grebnev on 02.11.2017.
 */
public class Vacancy {
    private Snippet snippet;
    private String name;
    private String createdAt;
    private Address address;
    private String idVacancy;

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

    public void setAddress(AddressNetwork address) {
        this.address = new Address();
        if (address != null) {
            this.address.setCity(address.getCity());
            this.address.setStreet(address.getStreet());
            this.address.setBuilding(address.getBuilding());
        }
    }

    public void setAddress(AddressLocal address) {
        this.address = new Address();
        if (address != null) {
            this.address.setCity(address.getCity());
            this.address.setStreet(address.getStreet());
            this.address.setBuilding(address.getBuilding());
        }
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

    public void setSnippet(SnippetNetwork snippet) {
        this.snippet = new Snippet();
        this.snippet.setRequirement(snippet.getRequirement());
        this.snippet.setResponsibility(snippet.getResponsibility());
    }

    public void setSnippet(SnippetLocal snippet) {
        this.snippet = new Snippet();
        this.snippet.setRequirement(snippet.getRequirement());
        this.snippet.setResponsibility(snippet.getResponsibility());
    }
}
