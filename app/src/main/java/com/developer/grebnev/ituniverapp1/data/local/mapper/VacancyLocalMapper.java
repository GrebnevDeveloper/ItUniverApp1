package com.developer.grebnev.ituniverapp1.data.local.mapper;

import com.developer.grebnev.ituniverapp1.data.local.model.AddressLocal;
import com.developer.grebnev.ituniverapp1.data.local.model.EmployerLocal;
import com.developer.grebnev.ituniverapp1.data.local.model.SalaryLocal;
import com.developer.grebnev.ituniverapp1.data.local.model.VacancyLocal;
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

public class VacancyLocalMapper {
    @Inject
    public VacancyLocalMapper() {
    }

    public List<Vacancy> transformListFromLocal(List<VacancyLocal> vacanciesLocal) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (VacancyLocal vacancyLocal : vacanciesLocal) {
            vacancies.add(transformFromLocal(vacancyLocal));
        }
        return vacancies;
    }

    public Vacancy transformFromLocal(VacancyLocal vacancyLocal) {
        Salary salary = new Salary(null, null, "Not specified salary");
        if (vacancyLocal.getSalary() != null) {
            salary = new Salary(vacancyLocal.getSalary().getTo(),
                    vacancyLocal.getSalary().getFrom(),
                    vacancyLocal.getSalary().getCurrency());
        }

        String description = "Not specified description";

        Address address = new Address("Not specified address", "", "");
        if (vacancyLocal.getAddress() != null) {
            address = new Address(vacancyLocal.getAddress().getBuilding(),
                    vacancyLocal.getAddress().getCity(),
                    vacancyLocal.getAddress().getStreet());
        }

        Employer employer = new Employer("Not specified employer");
        if (vacancyLocal.getEmployer() != null) {
            employer = new Employer(vacancyLocal.getEmployer().getName());
        }

        Vacancy vacancy = new Vacancy(salary,
                vacancyLocal.getName(),
                description,
                vacancyLocal.getCreatedAt(),
                address,
                employer,
                new Contacts("Not specified email", new Phone("Not specified phone", null, null)),
                vacancyLocal.getIdVacancy());
        return vacancy;
    }

    public List<VacancyLocal> transformListToLocal(List<Vacancy> vacancyList) {
        List<VacancyLocal> vacancyLocals = new ArrayList<>();
        for (Vacancy vacancy : vacancyList) {
            VacancyLocal vacancyLocal = new VacancyLocal();
            SalaryLocal salaryLocal = new SalaryLocal();
            if (vacancy.getSalary() != null) {
                salaryLocal.setTo(vacancy.getSalary().getTo());
                salaryLocal.setFrom(vacancy.getSalary().getFrom());
                salaryLocal.setCurrency(vacancy.getSalary().getCurrency());
            }
            vacancyLocal.setSalary(salaryLocal);

            AddressLocal addressLocal = new AddressLocal();
            if (vacancy.getAddress() != null) {
                addressLocal.setCity(vacancy.getAddress().getCity());
                addressLocal.setStreet(vacancy.getAddress().getStreet());
                addressLocal.setBuilding(vacancy.getAddress().getBuilding());
            }
            vacancyLocal.setAddress(addressLocal);

            EmployerLocal employerLocal = new EmployerLocal();
            if (vacancy.getEmployer() != null) {
                employerLocal.setName(vacancy.getEmployer().getName());
            }
            vacancyLocal.setEmployer(employerLocal);
            vacancyLocal.setName(vacancy.getName());
            vacancyLocal.setIdVacancy(vacancy.getIdVacancy());
            if (vacancy.getDescription() != null) {
                vacancyLocal.setDescription(vacancy.getDescription());
            }
            vacancyLocal.setCreatedAt(vacancy.getCreatedAt());

            vacancyLocals.add(vacancyLocal);
        }

        return vacancyLocals;
    }
}
