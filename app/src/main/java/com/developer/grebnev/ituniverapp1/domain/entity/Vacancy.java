package com.developer.grebnev.ituniverapp1.domain.entity;

/**
 * Created by Grebnev on 07.12.2017.
 */

public class Vacancy {
    private final Salary salary;
    private final String name;
    private final String description;
    private final String createdAt;
    private final Address address;
    private final Employer employer;
    private final Contacts contacts;
    private final String idVacancy;

    public Vacancy(Salary salary,
                   String name,
                   String description,
                   String createdAt,
                   Address address,
                   Employer employer,
                   Contacts contacts,
                   String idVacancy) {
        this.salary = salary;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.address = address;
        this.employer = employer;
        this.contacts = contacts;
        this.idVacancy = idVacancy;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public Address getAddress() {
        return address;
    }

    public String getIdVacancy() {
        return idVacancy;
    }

    public Salary getSalary() {
        return salary;
    }

    public Employer getEmployer() {
        return employer;
    }

    public Contacts getContacts() {
        return contacts;
    }
}
