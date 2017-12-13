package com.developer.grebnev.ituniverapp1.domain.mapper;

import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;
import com.developer.grebnev.ituniverapp1.presentation.mvp.model.Vacancy;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Grebnev on 05.11.2017.
 */

public class DequeVacancyMapper {
    @Inject
    public DequeVacancyMapper() {
    }

    public DequeVacancies createDequeVacancy(DequeVacancies dequeVacancies, Map<Integer, List<Vacancy>> mapVacancy, int route) {
        dequeVacancies.addElementIntoDeque(mapVacancy, route);
        return dequeVacancies;
    }
}
