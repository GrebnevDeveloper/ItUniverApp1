package com.developer.grebnev.ituniverapp1.domain.deque;

import android.app.Application;

import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.data.local.DataManager;
import com.developer.grebnev.ituniverapp1.data.local.DataQuery;
import com.developer.grebnev.ituniverapp1.data.repository.VacanciesNetworkRepository;
import com.developer.grebnev.ituniverapp1.domain.mapper.DequeVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.MapVacancyMapper;
import com.developer.grebnev.ituniverapp1.utils.InternetConnection;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 12.11.2017.
 */

public class DequeVacanciesLoader {
    Application application;
    VacanciesNetworkRepository vacanciesNetworkRepository;
    DataManager dataManager;
    DataQuery query;
    MapVacancyMapper mapVacancyMapper;
    DequeVacancyMapper dequeVacancyMapper;
    DequeVacancies dequeVacancies;

    @Inject
    public DequeVacanciesLoader(Application application,
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
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        Observable<DequeVacancies> vacanciesFromNetwork = Observable.empty();
        if (isInternetConnection()) {
            if (dequeVacancies.getMapTime().containsKey(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD)) {
                if (time - dequeVacancies.getMapTime().get(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD)
                            > 100 * 60 * 10) {
                    vacanciesFromNetwork = getDataFromNetwork(totalItemCountPresenter, route, time);
                }
            }
            else {
                vacanciesFromNetwork = getDataFromNetwork(totalItemCountPresenter, route, time);
            }
        }
        Observable<DequeVacancies> vacanciesFromLocal = Observable.just(dequeVacancies);
        if (query.getCountVacancies() != 0) {
            vacanciesFromLocal = getDataFromLocal(totalItemCountPresenter, route);
        }
        return vacanciesFromNetwork.switchIfEmpty(vacanciesFromLocal);
    }

    private Observable<DequeVacancies> getDataFromNetwork(int totalItemCountPresenter, int route, long time) {
        return vacanciesNetworkRepository.getVacanciesNetwork(EndlessRecyclerConstants.VOLUME_LOAD,
                totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD - 1)
                .doOnNext(listVacancies -> {
                    if (totalItemCountPresenter > query.getCountVacancies()) {
                        dataManager.saveData(listVacancies);
                    } else {
                        dataManager.overwriteData(listVacancies, totalItemCountPresenter);
                    }
                    dequeVacancies.writeTime(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD,
                            time);
                })
                .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route));
    }

    private Observable<DequeVacancies> getDataFromLocal(int totalItemCountPresenter, int route) {
        return query.getListVacancies(totalItemCountPresenter - EndlessRecyclerConstants.VOLUME_LOAD,
                totalItemCountPresenter + 1)
                .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route));
    }

    public boolean isInternetConnection() {
        if (InternetConnection.isOnline(application.getApplicationContext())) {
            return true;
        } else {
            return false;
        }
    }
}
