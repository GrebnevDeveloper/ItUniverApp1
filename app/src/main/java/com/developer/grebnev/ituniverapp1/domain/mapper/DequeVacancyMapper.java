package com.developer.grebnev.ituniverapp1.domain.mapper;

import android.util.Log;

import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.domain.interactor.DequeVacancies;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Grebnev on 05.11.2017.
 */

public class DequeVacancyMapper {
    @Inject
    public DequeVacancyMapper() {}
    public DequeVacancies createDequeVacancy(DequeVacancies dequeVacancies, Map<Integer, List<Vacancy>> mapVacancy, int route) {
        dequeVacancies.addElementIntoDeque(mapVacancy, route);
        Log.d("DVM", "Create deque " + dequeVacancies.getVacancyOfDeque(1).getIdVacancy());
        return dequeVacancies;
    }
}
