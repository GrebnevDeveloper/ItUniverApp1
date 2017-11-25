package com.developer.grebnev.ituniverapp1.data.repository;

import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.data.entity.mapper.VacancyEntityMapper;
import com.developer.grebnev.ituniverapp1.data.entity.mapper.VacancyJsonMapper;
import com.developer.grebnev.ituniverapp1.data.local.DataQueryInterface;
import com.developer.grebnev.ituniverapp1.data.network.RequestInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class VacanciesRepository implements VacanciesInterface{
    VacancyJsonMapper vacancyJsonMapper;
    VacancyEntityMapper vacancyEntityMapper;
    RequestInterface requestInterface;
    DataQueryInterface dataQueryInterface;

    @Inject
    public VacanciesRepository(VacancyJsonMapper vacancyJsonMapper,
                                      VacancyEntityMapper vacancyEntityMapper,
                                      RequestInterface requestInterface,
                                      DataQueryInterface dataQueryInterface) {
        this.vacancyJsonMapper = vacancyJsonMapper;
        this.vacancyEntityMapper = vacancyEntityMapper;
        this.requestInterface= requestInterface;
        this.dataQueryInterface = dataQueryInterface;
    }

    @Override
    public Observable<List<Vacancy>> getVacanciesNetwork(String textSearch, int countVacancies, int numberPage) {
        if (textSearch.equals("")) {
            return requestInterface.getVacancies(countVacancies, numberPage)
                    .map(pageVacancy -> vacancyJsonMapper.transformJsonToVacancy(pageVacancy))
                    .map(listVacanciesNetwork -> vacancyEntityMapper.transformFromNetwork(listVacanciesNetwork));
        }
        else {
            return requestInterface.getResultSearch(textSearch, countVacancies, numberPage)
                    .map(pageVacancy -> vacancyJsonMapper.transformJsonToVacancy(pageVacancy))
                    .map(listVacanciesNetwork -> vacancyEntityMapper.transformFromNetwork(listVacanciesNetwork));
        }
    }

    @Override
    public Observable<List<Vacancy>> getVacanciesLocal(int start, int end) {
        return dataQueryInterface.getListVacancies(start, end)
                .map(listVacanciesLocal -> vacancyEntityMapper.transformFromLocal(listVacanciesLocal));
    }
}
