package com.developer.grebnev.ituniverapp1.data.entity.mapper;

import com.developer.grebnev.ituniverapp1.data.entity.Address;
import com.developer.grebnev.ituniverapp1.data.entity.Contacts;
import com.developer.grebnev.ituniverapp1.data.entity.Employer;
import com.developer.grebnev.ituniverapp1.data.entity.Phone;
import com.developer.grebnev.ituniverapp1.data.entity.Salary;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.data.local.model.VacancyLocal;
import com.developer.grebnev.ituniverapp1.data.network.model.PhoneNetwork;
import com.developer.grebnev.ituniverapp1.data.network.model.VacancyNetwork;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Grebnev on 25.11.2017.
 */

public class VacancyEntityMapper {
    @Inject
    public VacancyEntityMapper() {
    }

    public List<Vacancy> transformListFromNetwork(List<VacancyNetwork> vacanciesNetwork) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (VacancyNetwork vacancyNetwork : vacanciesNetwork) {
            vacancies.add(transformFromNetwork(vacancyNetwork));
        }
        return vacancies;
    }

    public List<Vacancy> transformListFromLocal(List<VacancyLocal> vacanciesLocal) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (VacancyLocal vacancyLocal : vacanciesLocal) {
            vacancies.add(transformFromLocal(vacancyLocal));
        }
        return vacancies;
    }

    public Vacancy transformFromNetwork(VacancyNetwork vacancyNetwork) {
        Salary salary = null;
        if (vacancyNetwork.getSalary() != null) {
            salary = Salary.create(vacancyNetwork.getSalary().getTo(),
                    vacancyNetwork.getSalary().getFrom(),
                    vacancyNetwork.getSalary().getCurrency());
        }

        Address address = null;
        if (vacancyNetwork.getAddress() != null) {
            address = Address.create(vacancyNetwork.getAddress().getBuilding(),
                    vacancyNetwork.getAddress().getCity(),
                    vacancyNetwork.getAddress().getStreet());
        }

        Employer employer = null;
        if (vacancyNetwork.getEmployer() != null) {
            employer = Employer.create(vacancyNetwork.getEmployer().getName());
        }

        List<Phone> phones = new ArrayList<>();
        if (vacancyNetwork.getContacts() != null && vacancyNetwork.getContacts().getPhones() != null) {
            for (PhoneNetwork phoneNetwork : vacancyNetwork.getContacts().getPhones()) {
                Phone phone = Phone.create(phoneNetwork.getCountry(),
                        phoneNetwork.getCity(),
                        phoneNetwork.getNumber());
                phones.add(phone);
            }
        }
        Contacts contacts = null;
        if (vacancyNetwork.getContacts() != null) {
            contacts = Contacts.create(vacancyNetwork.getContacts().getEmail(), phones);
        }

        Vacancy vacancy = Vacancy.create(salary,
                vacancyNetwork.getName(),
                vacancyNetwork.getDescription(),
                vacancyNetwork.getCreatedAt(),
                address,
                employer,
                contacts,
                vacancyNetwork.getIdVacancy());
        return vacancy;
    }

    public Vacancy transformFromLocal(VacancyLocal vacancyLocal) {
        Salary salary = null;
        if (vacancyLocal.getSalary() != null) {
            salary = Salary.create(vacancyLocal.getSalary().getTo(),
                    vacancyLocal.getSalary().getFrom(),
                    vacancyLocal.getSalary().getCurrency());
        }

        Address address = null;
        if (vacancyLocal.getAddress() != null) {
            address = Address.create(vacancyLocal.getAddress().getBuilding(),
                    vacancyLocal.getAddress().getCity(),
                    vacancyLocal.getAddress().getStreet());
        }

        Employer employer = null;
        if (vacancyLocal.getEmployer() != null) {
            employer = Employer.create(vacancyLocal.getEmployer().getName());
        }

        Vacancy vacancy = Vacancy.create(salary,
                vacancyLocal.getName(),
                vacancyLocal.getDescription(),
                vacancyLocal.getCreatedAt(),
                address,
                employer,
                null,
                vacancyLocal.getIdVacancy());
        return vacancy;
    }
}
