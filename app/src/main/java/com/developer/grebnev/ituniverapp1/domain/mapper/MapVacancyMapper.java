package com.developer.grebnev.ituniverapp1.domain.mapper;

import com.developer.grebnev.ituniverapp1.presentation.mvp.model.VacancyPresentation;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Grebnev on 04.11.2017.
 */

public class MapVacancyMapper {
    @Inject
    public MapVacancyMapper() {
    }

    public Map<Integer, List<VacancyPresentation>> createMapVacancies(int totalItemCountPresenter, List<VacancyPresentation> vacancies) {
        Map<Integer, List<VacancyPresentation>> mapVacancies = new HashMap<>();
        mapVacancies.put(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD, vacancies);
        return mapVacancies;
    }
}
