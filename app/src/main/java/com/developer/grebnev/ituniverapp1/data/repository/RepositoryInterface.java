package com.developer.grebnev.ituniverapp1.data.repository;


import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 11.11.2017.
 */

public interface RepositoryInterface {
    Observable<List<Vacancy>> getVacanciesNetwork(int countVacancies, int numberPage);

    Observable<List<Vacancy>> getVacanciesLocal(int start, int end);

    Observable<List<Vacancy>> getVacanciesSearch(String textSearch, int countVacancies, int numberPage);

    Observable<Vacancy> getVacancyDetail(String vacancyId);
}
