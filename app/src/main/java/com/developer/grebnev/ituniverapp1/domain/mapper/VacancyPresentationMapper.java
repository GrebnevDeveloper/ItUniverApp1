package com.developer.grebnev.ituniverapp1.domain.mapper;

import com.developer.grebnev.ituniverapp1.domain.entity.Address;
import com.developer.grebnev.ituniverapp1.domain.entity.Phone;
import com.developer.grebnev.ituniverapp1.domain.entity.Salary;
import com.developer.grebnev.ituniverapp1.presentation.mvp.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Grebnev on 07.12.2017.
 */

public class VacancyPresentationMapper {
    @Inject
    public VacancyPresentationMapper() {
    }

    public List<Vacancy> transformListFromEntity(List<com.developer.grebnev.ituniverapp1.domain.entity.Vacancy> vacancies) {
        List<Vacancy> vacancyList = new ArrayList<>();
        for (com.developer.grebnev.ituniverapp1.domain.entity.Vacancy vacancy : vacancies) {
            vacancyList.add(transformFromEntity(vacancy));
        }
        return vacancyList;
    }

    public Vacancy transformFromEntity(com.developer.grebnev.ituniverapp1.domain.entity.Vacancy vacancy) {
        return Vacancy.create(
                buildSalaryStr(vacancy.getSalary()),
                vacancy.getName(),
                vacancy.getDescription(),
                buildCreatedAtStr(vacancy.getCreatedAt()),
                buildAddressStr(vacancy.getAddress()),
                vacancy.getEmployer().getName(),
                vacancy.getContacts().getEmail(),
                buildPhoneStr(vacancy.getContacts().getPhone()),
                vacancy.getIdVacancy()
        );
    }

    private String buildSalaryStr(Salary salary) {
        String vacancyStr = "";
        if (salary.getFrom() != null) {
            vacancyStr += "from " + salary.getFrom() + " ";
        }
        if (salary.getTo() != null) {
            vacancyStr += "to " + salary.getTo() + " ";
        }
        vacancyStr += salary.getCurrency();
        return vacancyStr;
    }

    private String buildCreatedAtStr(String createdAt) {
        return createdAt.substring(0, 10) + " in " + createdAt.substring(12, 16);
    }

    private String buildAddressStr(Address address) {
        String addressStr = "";
        if (address.getCity() != null && !address.getCity().equals("")) {
            addressStr += address.getCity() + ", ";
        } else {
            addressStr += address.getCity();
        }
        if (address.getStreet() != null && !address.getStreet().equals("")) {
            addressStr += address.getStreet() + ", ";
        }
        if (address.getBuilding() != null && !address.getBuilding().equals("")) {
            addressStr += address.getBuilding();
        }
        return addressStr;
    }

    private String buildPhoneStr(Phone phone) {
        String phoneStr = "";
        if (phone.getCountry() != null) {
            phoneStr += phone.getCountry();
        }
        if (phone.getCity() != null) {
            phoneStr += phone.getCity();
        }
        if (phone.getNumber() != null) {
            phoneStr += phone.getNumber();
        }
        return phoneStr;
    }
}
