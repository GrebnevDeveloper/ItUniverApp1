package com.developer.grebnev.ituniverapp1.domain.deque;


import com.developer.grebnev.ituniverapp1.presentation.mvp.model.VacancyPresentation;

import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * Created by Grebnev on 12.11.2017.
 */

public interface DequeVacanciesInterface {
    Deque<Map<Integer, List<VacancyPresentation>>> getDequeVacancies();

    void addElementIntoDeque(Map<Integer, List<VacancyPresentation>> vacancies, int route);

    VacancyPresentation getVacancyOfDeque(int position);

    void writeTime(int page, long time);

    Map<Integer, Long> getMapTime();

    String getOldTextSearch();

    void setOldTextSearch(String textSearch);

    int getItemCount();
}
