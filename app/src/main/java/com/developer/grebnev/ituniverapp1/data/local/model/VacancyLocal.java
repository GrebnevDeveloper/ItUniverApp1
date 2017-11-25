package com.developer.grebnev.ituniverapp1.data.local.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.developer.grebnev.ituniverapp1.data.entity.Address;
import com.developer.grebnev.ituniverapp1.data.entity.Snippet;

/**
 * Created by Grebnev on 02.11.2017.
 */
@Table(name = "Vacancy")
public class VacancyLocal extends Model{
    @Column(name = "snippet")
    private SnippetLocal snippet;
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "address")
    private AddressLocal address;
    @Column(name = "id_vacancy")
    private String idVacancy;

    public VacancyLocal() {
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

    public AddressLocal getAddress() {
        return address;
    }

    public void setAddress(AddressLocal address) {
        this.address = address;
    }

    public void setAddress(Address address) {
        this.address = new AddressLocal();
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

    public SnippetLocal getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetLocal snippet) {
        this.snippet = snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = new SnippetLocal();
        this.snippet.setRequirement(snippet.getRequirement());
        this.snippet.setResponsibility(snippet.getResponsibility());
    }
}
