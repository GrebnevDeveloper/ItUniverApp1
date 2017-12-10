package com.developer.grebnev.ituniverapp1.domain.deque;


import com.developer.grebnev.ituniverapp1.presentation.mvp.model.Vacancy;

import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * Created by Grebnev on 12.11.2017.
 */

public interface DequeVacanciesInterface {
    Deque<Map<Integer, List<Vacancy>>> getDequeVacancies();
    void addElementIntoDeque(Map<Integer, List<Vacancy>> vacancies, int route);
    Vacancy getVacancyOfDeque(int position);
    void writeTime(int page, long time);
    Map<Integer, Long> getMapTime();
    void setOldTextSearch(String textSearch);
    String getOldTextSearch();
    int getItemCount();
}
