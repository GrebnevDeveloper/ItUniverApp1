package com.developer.grebnev.ituniverapp1.data.network.mapper;

import com.developer.grebnev.ituniverapp1.data.network.model.PageVacancyNetwork;
import com.developer.grebnev.ituniverapp1.data.network.model.PhoneNetwork;
import com.developer.grebnev.ituniverapp1.data.network.model.VacancyNetwork;
import com.developer.grebnev.ituniverapp1.domain.entity.Address;
import com.developer.grebnev.ituniverapp1.domain.entity.Contacts;
import com.developer.grebnev.ituniverapp1.domain.entity.Employer;
import com.developer.grebnev.ituniverapp1.domain.entity.Phone;
import com.developer.grebnev.ituniverapp1.domain.entity.Salary;
import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Grebnev on 09.12.2017.
 */

public class VacancyNetworkMapper {
    @Inject
    public VacancyNetworkMapper() {
    }

    public List<VacancyNetwork> transformJsonToVacancy(PageVacancyNetwork pageVacancy) {
        return pageVacancy.getVacanciesList();
    }

    public List<Vacancy> transformListFromNetwork(List<VacancyNetwork> vacanciesNetwork) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (VacancyNetwork vacancyNetwork : vacanciesNetwork) {
            vacancies.add(transformFromNetwork(vacancyNetwork));
        }
        return vacancies;
    }

    public Vacancy transformFromNetwork(VacancyNetwork vacancyNetwork) {
        Salary salary = new Salary(null, null, "Not specified salary");
        if (vacancyNetwork.getSalary() != null) {
            salary = new Salary(vacancyNetwork.getSalary().getTo(),
                    vacancyNetwork.getSalary().getFrom(),
                    vacancyNetwork.getSalary().getCurrency());
        }

        String description = "Not specified description";
        if (vacancyNetwork.getDescription() != null) {
            description = vacancyNetwork.getDescription();
        }

        Address address = new Address("Not specified address", "", "");
        ;
        if (vacancyNetwork.getAddress() != null) {
            address = new Address(vacancyNetwork.getAddress().getBuilding(),
                    vacancyNetwork.getAddress().getCity(),
                    vacancyNetwork.getAddress().getStreet());
        }

        Employer employer = new Employer("Not specified employer");
        if (vacancyNetwork.getEmployer() != null) {
            employer = new Employer(vacancyNetwork.getEmployer().getName());
        }

        List<Phone> phones = new ArrayList<>();
        if (vacancyNetwork.getContacts() != null && vacancyNetwork.getContacts().getPhones() != null) {
            for (PhoneNetwork phoneNetwork : vacancyNetwork.getContacts().getPhones()) {
                Phone phone = new Phone(phoneNetwork.getCountry(),
                        phoneNetwork.getCity(),
                        phoneNetwork.getNumber());
                phones.add(phone);
            }
        }
        Contacts contacts = new Contacts("Not specified email", new Phone("Not specified phone", null, null));
        if (vacancyNetwork.getContacts() != null) {
            contacts = new Contacts(vacancyNetwork.getContacts().getEmail(), phones.get(0));
        }

        Vacancy vacancy = new Vacancy(salary,
                vacancyNetwork.getName(),
                description,
                vacancyNetwork.getCreatedAt(),
                address,
                employer,
                contacts,
                vacancyNetwork.getIdVacancy());
        return vacancy;
    }
}
