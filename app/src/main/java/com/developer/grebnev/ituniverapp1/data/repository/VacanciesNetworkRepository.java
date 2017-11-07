package com.developer.grebnev.ituniverapp1.data.repository;

import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.data.entity.mapper.VacancyJsonMapper;
import com.developer.grebnev.ituniverapp1.data.network.RetrofitManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class VacanciesNetworkRepository {
    VacancyJsonMapper vacancyJsonMapper;

    @Inject
    public VacanciesNetworkRepository(VacancyJsonMapper vacancyJsonMapper) {
        this.vacancyJsonMapper = vacancyJsonMapper;
    }

    public Observable<List<Vacancy>> getVacanciesNetwork(int countVacancies, int numberPage) {
        return RetrofitManager.getRequestInterface().getVacancies(countVacancies, numberPage)
                .map(pageVacancy -> vacancyJsonMapper.transformJsonToVacancy(pageVacancy));
    }
}
