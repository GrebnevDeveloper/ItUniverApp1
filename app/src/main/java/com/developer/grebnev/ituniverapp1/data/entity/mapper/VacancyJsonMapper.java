package com.developer.grebnev.ituniverapp1.data.entity.mapper;

import com.developer.grebnev.ituniverapp1.data.network.model.PageVacancyNetwork;
import com.developer.grebnev.ituniverapp1.data.network.model.VacancyNetwork;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Grebnev on 04.11.2017.
 */

public class VacancyJsonMapper {
    @Inject
    public VacancyJsonMapper() {}

    public List<VacancyNetwork> transformJsonToVacancy(PageVacancyNetwork pageVacancy) {
        return pageVacancy.getVacanciesList();
    }
}
