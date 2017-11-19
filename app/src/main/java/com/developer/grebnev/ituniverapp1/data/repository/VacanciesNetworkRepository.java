package com.developer.grebnev.ituniverapp1.data.repository;

import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.data.entity.mapper.VacancyJsonMapper;
import com.developer.grebnev.ituniverapp1.data.network.RequestInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class VacanciesNetworkRepository implements VacanciesNetworkInterface{
    VacancyJsonMapper vacancyJsonMapper;
    RequestInterface requestInterface;

    @Inject
    public VacanciesNetworkRepository(VacancyJsonMapper vacancyJsonMapper, RequestInterface requestInterface) {
        this.vacancyJsonMapper = vacancyJsonMapper;
        this.requestInterface= requestInterface;
    }

    @Override
    public Observable<List<Vacancy>> getVacanciesNetwork(String textSearch, int countVacancies, int numberPage) {
        if (textSearch.equals("")) {
            return requestInterface.getVacancies(countVacancies, numberPage)
                    .map(pageVacancy -> vacancyJsonMapper.transformJsonToVacancy(pageVacancy));
        }
        else {
            return requestInterface.getResultSearch(textSearch, countVacancies, numberPage)
                    .map(pageVacancy -> vacancyJsonMapper.transformJsonToVacancy(pageVacancy));
        }
    }
}
