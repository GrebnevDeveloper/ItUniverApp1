package com.developer.grebnev.ituniverapp1.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grebnev on 02.11.2017.
 */
public class VacancyNetwork {
    @SerializedName("salary")
    private SalaryNetwork salary;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("address")
    private AddressNetwork address;
    @SerializedName("employer")
    private EmployerNetwork employer;
    @SerializedName("contacts")
    private ContactsNetwork contacts;
    @SerializedName("id")
    private String idVacancy;

    public VacancyNetwork() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressNetwork getAddress() {
        return address;
    }

    public void setAddress(AddressNetwork address) {
        this.address = address;
    }

    public String getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(String id) {
        this.idVacancy = id;
    }

    public SalaryNetwork getSalary() {
        return salary;
    }

    public void setSalary(SalaryNetwork salary) {
        this.salary = salary;
    }

    public EmployerNetwork getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerNetwork employer) {
        this.employer = employer;
    }

    public ContactsNetwork getContacts() {
        return contacts;
    }

    public void setContacts(ContactsNetwork contacts) {
        this.contacts = contacts;
    }
}
