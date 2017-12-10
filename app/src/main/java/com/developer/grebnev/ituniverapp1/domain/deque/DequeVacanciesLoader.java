package com.developer.grebnev.ituniverapp1.domain.deque;

import android.app.Application;

import com.developer.grebnev.ituniverapp1.data.local.DataManagerInterface;
import com.developer.grebnev.ituniverapp1.data.local.DataQueryInterface;
import com.developer.grebnev.ituniverapp1.data.repository.RepositoryInterface;
import com.developer.grebnev.ituniverapp1.domain.mapper.DequeVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.MapVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.VacancyPresentationMapper;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.utils.InternetConnection;

import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Grebnev on 12.11.2017.
 */
@Singleton
public class DequeVacanciesLoader implements DequeLoaderInterface {
    Application application;
    RepositoryInterface repositoryInterface;
    DataManagerInterface dataManagerInterface;
    DataQueryInterface queryInterface;
    MapVacancyMapper mapVacancyMapper;
    DequeVacancyMapper dequeVacancyMapper;
    DequeVacanciesInterface dequeVacanciesInterface;
    VacancyPresentationMapper vacancyPresentationMapper;
    CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public DequeVacanciesLoader(Application application,
                                RepositoryInterface repositoryInterface,
                                DataManagerInterface dataManagerInterface,
                                DataQueryInterface queryInterface,
                                MapVacancyMapper mapVacancyMapper,
                                DequeVacancyMapper dequeVacancyMapper,
                                DequeVacanciesInterface dequeVacanciesInterface,
                                VacancyPresentationMapper vacancyPresentationMapper) {
        this.application = application;
        this.repositoryInterface = repositoryInterface;
        this.dataManagerInterface = dataManagerInterface;
        this.queryInterface = queryInterface;
        this.mapVacancyMapper = mapVacancyMapper;
        this.dequeVacancyMapper = dequeVacancyMapper;
        this.dequeVacanciesInterface = dequeVacanciesInterface;
        this.vacancyPresentationMapper = vacancyPresentationMapper;
    }

    private DequeVacancies dequeVacancies = new DequeVacancies();

    @Override
    public Observable<DequeVacancies> loadVacancies(String textSearch, int totalItemCountPresenter, int route) {
        Observable<DequeVacancies> vacanciesFromNetwork = Observable.empty();
        if (isInternetConnection()) {
            if (dequeVacancies.getMapTime().containsKey(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD) &&
                    dequeVacancies.getOldTextSearch().equals(textSearch)) {
                if (getCurrentTime() - dequeVacancies.getMapTime().get(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD)
                        > 100 * 60 * 10 && route == EndlessRecyclerConstants.SCROLL_DOWN) {
                    vacanciesFromNetwork = getDataFromNetwork(textSearch, totalItemCountPresenter, route, getCurrentTime());
                }
            } else {
                dequeVacancies.setOldTextSearch(textSearch);
                vacanciesFromNetwork = getDataFromNetwork(textSearch, totalItemCountPresenter, route, getCurrentTime());
            }
        }
        Observable<DequeVacancies> vacanciesFromLocal = Observable.just(dequeVacancies);
        if (queryInterface.getCountVacancies() != 0) {
            vacanciesFromLocal = getDataFromLocal(totalItemCountPresenter, route);
        }
        return vacanciesFromNetwork.switchIfEmpty(vacanciesFromLocal);
    }

    private Observable<DequeVacancies> getDataFromNetwork(String textSearch, int totalItemCountPresenter, int route, long time) {
        return repositoryInterface.getVacanciesNetwork(textSearch, EndlessRecyclerConstants.VOLUME_LOAD,
                totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD - 1)
                .doOnNext(listVacancies -> {
                    if (totalItemCountPresenter > queryInterface.getCountVacancies()) {
                        disposable.add(dataManagerInterface.saveData(listVacancies)
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                    } else {
                        disposable.add(dataManagerInterface.overwriteData(listVacancies, totalItemCountPresenter)
                                .subscribeOn(Schedulers.io())
                                .subscribe());
                    }
                    dequeVacancies.writeTime(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD,
                            time);
                })
                .map(listVacancies -> vacancyPresentationMapper.transformListFromEntity(listVacancies))
                .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route));
    }

    private Observable<DequeVacancies> getDataFromLocal(int totalItemCountPresenter, int route) {
        return repositoryInterface.getVacanciesLocal(totalItemCountPresenter - EndlessRecyclerConstants.VOLUME_LOAD,
                totalItemCountPresenter + 1)
                .map(listVacancies -> vacancyPresentationMapper.transformListFromEntity(listVacancies))
                .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route));
    }

    private long getCurrentTime() {
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        return time;
    }

    @Override
    public boolean isInternetConnection() {
        if (InternetConnection.isOnline(application.getApplicationContext())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public CompositeDisposable getCompositeDisposableData() {
        return disposable;
    }
}
