package com.developer.grebnev.ituniverapp1.domain.mapper;

import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Grebnev on 04.11.2017.
 */

public class MapVacancyMapper {
    @Inject
    public MapVacancyMapper() {}

    public Map<Integer, List<Vacancy>> createMapVacancies(int totalItemCountPresenter, List<Vacancy> vacancies) {
        Map<Integer, List<Vacancy>> mapVacancies = new HashMap<>();
        mapVacancies.put(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD, vacancies);
        return mapVacancies;
    }
}
