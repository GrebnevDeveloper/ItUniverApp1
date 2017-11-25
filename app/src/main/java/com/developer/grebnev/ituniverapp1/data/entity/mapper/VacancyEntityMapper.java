package com.developer.grebnev.ituniverapp1.data.entity.mapper;

import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.data.local.model.VacancyLocal;
import com.developer.grebnev.ituniverapp1.data.network.model.VacancyNetwork;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Grebnev on 25.11.2017.
 */

public class VacancyEntityMapper {
    @Inject
    public VacancyEntityMapper() {}

    public List<Vacancy> transformFromNetwork(List<VacancyNetwork> vacancySource) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Object vacancyIndefined : vacancySource) {
            vacancies.add(transform(vacancyIndefined));
        }
        return vacancies;
    }

    public List<Vacancy> transformFromLocal(List<VacancyLocal> vacancySource) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Object vacancyIndefined : vacancySource) {
            vacancies.add(transform(vacancyIndefined));
        }
        return vacancies;
    }

    private Vacancy transform(Object vacancyIndefined) {
        Vacancy vacancy = new Vacancy();
        if (vacancyIndefined instanceof VacancyNetwork) {
            VacancyNetwork vacancySource = (VacancyNetwork) vacancyIndefined;
            vacancy.setIdVacancy(vacancySource.getIdVacancy());
            vacancy.setName(vacancySource.getName());
            vacancy.setCreatedAt(vacancySource.getCreatedAt());
            vacancy.setSnippet(vacancySource.getSnippet());
            vacancy.setAddress(vacancySource.getAddress());
        }
        else if (vacancyIndefined instanceof VacancyLocal) {
            VacancyLocal vacancySource = (VacancyLocal) vacancyIndefined;
            vacancy.setIdVacancy(vacancySource.getIdVacancy());
            vacancy.setName(vacancySource.getName());
            vacancy.setCreatedAt(vacancySource.getCreatedAt());
            vacancy.setSnippet(vacancySource.getSnippet());
            vacancy.setAddress(vacancySource.getAddress());
        }
        return vacancy;
    }
}
