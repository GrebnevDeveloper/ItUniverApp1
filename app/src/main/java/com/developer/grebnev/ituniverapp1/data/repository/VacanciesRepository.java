package com.developer.grebnev.ituniverapp1.data.repository;

import com.developer.grebnev.ituniverapp1.data.local.DataQueryInterface;
import com.developer.grebnev.ituniverapp1.data.local.mapper.VacancyLocalMapper;
import com.developer.grebnev.ituniverapp1.data.network.RequestInterface;
import com.developer.grebnev.ituniverapp1.data.network.mapper.VacancyNetworkMapper;
import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 07.11.2017.
 */
@Singleton
public class VacanciesRepository implements RepositoryInterface {
    private VacancyNetworkMapper vacancyNetworkMapper;
    private VacancyLocalMapper vacancyLocalMapper;
    private RequestInterface requestInterface;
    private DataQueryInterface dataQueryInterface;

    @Inject
    public VacanciesRepository(VacancyNetworkMapper vacancyNetworkMapper,
                               VacancyLocalMapper vacancyLocalMapper,
                               RequestInterface requestInterface,
                               DataQueryInterface dataQueryInterface) {
        this.vacancyNetworkMapper = vacancyNetworkMapper;
        this.vacancyLocalMapper = vacancyLocalMapper;
        this.requestInterface = requestInterface;
        this.dataQueryInterface = dataQueryInterface;
    }

    @Override
    public Observable<List<Vacancy>> getVacanciesNetwork(String textSearch, int countVacancies, int numberPage) {
        if (textSearch.equals("")) {
            return requestInterface.getVacancies(countVacancies, numberPage)
                    .map(pageVacancy -> vacancyNetworkMapper.transformJsonToVacancy(pageVacancy))
                    .map(listVacanciesNetwork -> vacancyNetworkMapper.transformListFromNetwork(listVacanciesNetwork));
        } else {
            return requestInterface.getResultSearch(textSearch, countVacancies, numberPage)
                    .map(pageVacancy -> vacancyNetworkMapper.transformJsonToVacancy(pageVacancy))
                    .map(listVacanciesNetwork -> vacancyNetworkMapper.transformListFromNetwork(listVacanciesNetwork));
        }
    }

    @Override
    public Observable<List<Vacancy>> getVacanciesLocal(int start, int end) {
        return dataQueryInterface.getListVacancies(start, end)
                .map(listVacanciesLocal -> vacancyLocalMapper.transformListFromLocal(listVacanciesLocal));
    }

    @Override
    public Observable<Vacancy> getVacancyDetail(String vacancyId) {
        return requestInterface.getVacancyDetail(vacancyId)
                .map(vacancyNetwork -> vacancyNetworkMapper.transformFromNetwork(vacancyNetwork));
    }
}
