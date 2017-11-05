package com.developer.grebnev.ituniverapp1.data.entity.mapper;

import com.developer.grebnev.ituniverapp1.data.entity.PageVacancy;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Grebnev on 04.11.2017.
 */

public class VacancyJsonMapper {
    @Inject
    public VacancyJsonMapper() {}

    public List<Vacancy> transformJsonToVacancy(PageVacancy pageVacancy) {
        return pageVacancy.getVacanciesList();
    }
}
