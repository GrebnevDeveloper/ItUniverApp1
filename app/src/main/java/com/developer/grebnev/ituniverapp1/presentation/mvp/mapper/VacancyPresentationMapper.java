package com.developer.grebnev.ituniverapp1.presentation.mvp.mapper;

import android.app.Application;

import com.developer.grebnev.ituniverapp1.R;
import com.developer.grebnev.ituniverapp1.domain.entity.Address;
import com.developer.grebnev.ituniverapp1.domain.entity.Phone;
import com.developer.grebnev.ituniverapp1.domain.entity.Salary;
import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.presentation.mvp.model.VacancyPresentation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Grebnev on 07.12.2017.
 */

public class VacancyPresentationMapper {
    Application application;

    @Inject
    public VacancyPresentationMapper(Application application) {
        this.application = application;
    }

    public List<VacancyPresentation> transformListFromEntity(List<Vacancy> vacancies) {
        List<VacancyPresentation> vacancyList = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            vacancyList.add(transformFromEntity(vacancy));
        }
        return vacancyList;
    }

    public VacancyPresentation transformFromEntity(Vacancy vacancy) {
        return VacancyPresentation.create(
                buildSalaryStr(vacancy.getSalary()),
                vacancy.getName(),
                buildDescriptionStr(vacancy.getDescription()),
                buildCreatedAtStr(vacancy.getCreatedAt()),
                buildAddressStr(vacancy.getAddress()),
                buildEmployerStr(vacancy.getEmployer().getName()),
                buildEmailStr(vacancy.getContacts().getEmail()),
                buildPhoneStr(vacancy.getContacts().getPhone()),
                vacancy.getIdVacancy()
        );
    }

    private String buildSalaryStr(Salary salary) {
        String vacancyStr = "";
        if (salary.getFrom() != null) {
            vacancyStr += application.getString(R.string.from) + salary.getFrom() + " ";
        }
        if (salary.getTo() != null) {
            vacancyStr += application.getString(R.string.to) + salary.getTo() + " ";
        }
        if (!salary.getCurrency().equals("")) {
            vacancyStr += salary.getCurrency();
        }
        else {
            vacancyStr += application.getString(R.string.salary_not_specified);
        }
        return vacancyStr;
    }

    private String buildCreatedAtStr(String createdAt) {
        return createdAt.substring(0, 10) + application.getString(R.string.in) + createdAt.substring(12, 16);
    }

    private String buildDescriptionStr(String description) {
        if (!description.equals("")) {
            return description;
        }
        else {
            return application.getString(R.string.not_description_specified);
        }
    }

    private String buildAddressStr(Address address) {
        String addressStr = "";
        if (address.getCity() != null && !address.getCity().equals("")) {
            addressStr += address.getCity() + ", ";
        } else {
            addressStr += application.getString(R.string.address_not_specified);
        }
        if (address.getStreet() != null && !address.getStreet().equals("")) {
            addressStr += address.getStreet() + ", ";
        }
        if (address.getBuilding() != null && !address.getBuilding().equals("")) {
            addressStr += address.getBuilding();
        }
        return addressStr;
    }

    private String buildEmployerStr(String employer) {
        if (!employer.equals("")) {
            return employer;
        }
        else {
            return application.getString(R.string.employer_not_specified);
        }
    }

    private String buildEmailStr(String email) {
        if (!email.equals("")) {
            return email;
        }
        else {
            return application.getString(R.string.not_email);
        }
    }

    private String buildPhoneStr(Phone phone) {
        String phoneStr = "";
        if (phone.getCountry() != null && !phone.getCountry().equals("")) {
            phoneStr += phone.getCountry();
        }
        else {
            phoneStr += application.getString(R.string.not_phone);
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
