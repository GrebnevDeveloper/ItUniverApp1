package com.developer.grebnev.ituniverapp1.domain.repository;

import android.app.Application;

import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.data.local.DataManager;
import com.developer.grebnev.ituniverapp1.data.local.DataQuery;
import com.developer.grebnev.ituniverapp1.data.repository.VacanciesNetworkRepository;
import com.developer.grebnev.ituniverapp1.domain.mapper.DequeVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.MapVacancyMapper;
import com.developer.grebnev.ituniverapp1.utils.InternetConnection;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class DequeVacanciesRepository {
    Application application;
    VacanciesNetworkRepository vacanciesNetworkRepository;
    DataManager dataManager;
    DataQuery query;
    MapVacancyMapper mapVacancyMapper;
    DequeVacancyMapper dequeVacancyMapper;
    DequeVacancies dequeVacancies;

    @Inject
    public DequeVacanciesRepository(Application application,
                                    VacanciesNetworkRepository vacanciesNetworkRepository,
                                    DataManager dataManager,
                                    DataQuery query,
                                    MapVacancyMapper mapVacancyMapper,
                                    DequeVacancyMapper dequeVacancyMapper,
                                    DequeVacancies dequeVacancies) {
        this.application = application;
        this.vacanciesNetworkRepository = vacanciesNetworkRepository;
        this.dataManager = dataManager;
        this.query = query;
        this.mapVacancyMapper = mapVacancyMapper;
        this.dequeVacancyMapper = dequeVacancyMapper;
        this.dequeVacancies = dequeVacancies;
    }

    public Observable<DequeVacancies> loadVacancies(int totalItemCountPresenter, int route) {
        Observable<DequeVacancies> vacanciesFromNetwork = Observable.empty();
        if (InternetConnection.isOnline(application.getApplicationContext()) && route == EndlessRecyclerConstants.SCROLL_DOWN) {
            vacanciesFromNetwork =
                    vacanciesNetworkRepository.getVacanciesNetwork(EndlessRecyclerConstants.VOLUME_LOAD,
                            totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD - 1)
                            .doOnNext(listVacancies -> {
                                if (totalItemCountPresenter > query.getCountVacancies()) {
                                    dataManager.saveData(listVacancies);
                                } else {
                                    dataManager.overwriteData(listVacancies, totalItemCountPresenter);
                                }
                            })
                            .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                            .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route));
        }
        Observable<DequeVacancies> vacanciesFromLocal = Observable.just(dequeVacancies);
        if (query.getCountVacancies() != 0) {
            vacanciesFromLocal =
                    query.getListVacancies(totalItemCountPresenter - EndlessRecyclerConstants.VOLUME_LOAD,
                            totalItemCountPresenter + 1)
                            .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                            .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route));
        }
        return vacanciesFromNetwork.switchIfEmpty(vacanciesFromLocal);
    }

    public boolean isInternetConnection() {
        if (InternetConnection.isOnline(application.getApplicationContext())) {
            return true;
        }
        else {
            return false;
        }
    }
}
