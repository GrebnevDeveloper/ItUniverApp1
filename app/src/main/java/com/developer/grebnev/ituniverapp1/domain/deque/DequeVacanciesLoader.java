package com.developer.grebnev.ituniverapp1.domain.deque;

import android.app.Application;

import com.developer.grebnev.ituniverapp1.data.local.DataManager;
import com.developer.grebnev.ituniverapp1.data.local.DataQuery;
import com.developer.grebnev.ituniverapp1.data.repository.VacanciesRepository;
import com.developer.grebnev.ituniverapp1.domain.mapper.DequeVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.MapVacancyMapper;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.utils.InternetConnection;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 12.11.2017.
 */

public class DequeVacanciesLoader {
    Application application;
    VacanciesRepository vacanciesRepository;
    DataManager dataManager;
    DataQuery query;
    MapVacancyMapper mapVacancyMapper;
    DequeVacancyMapper dequeVacancyMapper;
    DequeVacancies dequeVacancies;

    @Inject
    public DequeVacanciesLoader(Application application,
                                VacanciesRepository vacanciesRepository,
                                DataManager dataManager,
                                DataQuery query,
                                MapVacancyMapper mapVacancyMapper,
                                DequeVacancyMapper dequeVacancyMapper,
                                DequeVacancies dequeVacancies) {
        this.application = application;
        this.vacanciesRepository = vacanciesRepository;
        this.dataManager = dataManager;
        this.query = query;
        this.mapVacancyMapper = mapVacancyMapper;
        this.dequeVacancyMapper = dequeVacancyMapper;
        this.dequeVacancies = dequeVacancies;
    }

    public Observable<DequeVacancies> loadVacancies(String textSearch, int totalItemCountPresenter, int route) {
        Observable<DequeVacancies> vacanciesFromNetwork = Observable.empty();
        if (isInternetConnection()) {
            if (dequeVacancies.getMapTime().containsKey(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD) &&
                    dequeVacancies.getOldTextSearch().equals(textSearch)) {
                if (getCurrentTime() - dequeVacancies.getMapTime().get(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD)
                            > 100 * 60 * 10) {
                    vacanciesFromNetwork = getDataFromNetwork(textSearch, totalItemCountPresenter, route, getCurrentTime());
                }
            }
            else {
                dequeVacancies.setOldTextSearch(textSearch);
                vacanciesFromNetwork = getDataFromNetwork(textSearch, totalItemCountPresenter, route, getCurrentTime());
            }
        }
       // Single<DequeVacancies> vacanciesSingle = Single.fromCallable(() -> dequeVacancies);
        Observable<DequeVacancies> vacanciesFromLocal = Observable.just(dequeVacancies);
        if (query.getCountVacancies() != 0) {
            vacanciesFromLocal = getDataFromLocal(totalItemCountPresenter, route);
        }
        return vacanciesFromNetwork.switchIfEmpty(vacanciesFromLocal);
    }

    private Observable<DequeVacancies> getDataFromNetwork(String textSearch, int totalItemCountPresenter, int route, long time) {
        return vacanciesRepository.getVacanciesNetwork(textSearch, EndlessRecyclerConstants.VOLUME_LOAD,
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
        return vacanciesRepository.getVacanciesLocal(totalItemCountPresenter - EndlessRecyclerConstants.VOLUME_LOAD,
                totalItemCountPresenter + 1)
                .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route));
    }

    private long getCurrentTime() {
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        return time;
    }

    public boolean isInternetConnection() {
        if (InternetConnection.isOnline(application.getApplicationContext())) {
            return true;
        } else {
            return false;
        }
    }
}
