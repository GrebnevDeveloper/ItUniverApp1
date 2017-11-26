package com.developer.grebnev.ituniverapp1.data.local.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.developer.grebnev.ituniverapp1.data.entity.Address;

/**
 * Created by Grebnev on 02.11.2017.
 */
@Table(name = "Vacancy")
public class VacancyLocal extends Model{
    @Column(name = "salary")
    private SalaryLocal salary;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "address")
    private AddressLocal address;
    @Column(name = "employer")
    private EmployerLocal employer;
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
            this.address.setCity(address.city());
            this.address.setStreet(address.street());
            this.address.setBuilding(address.building());
        }
    }

    public SalaryLocal getSalary() {
        return salary;
    }

    public void setSalary(SalaryLocal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EmployerLocal getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerLocal employer) {
        this.employer = employer;
    }

    public String getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(String id) {
        this.idVacancy = id;
    }
}
