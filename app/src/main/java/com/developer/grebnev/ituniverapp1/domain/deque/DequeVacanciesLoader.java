package com.developer.grebnev.ituniverapp1.domain.deque;

import android.app.Application;
import android.util.Log;

import com.developer.grebnev.ituniverapp1.data.local.DataManagerInterface;
import com.developer.grebnev.ituniverapp1.data.local.DataQueryInterface;
import com.developer.grebnev.ituniverapp1.data.repository.RepositoryInterface;
import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.domain.mapper.DequeVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.MapVacancyMapper;
import com.developer.grebnev.ituniverapp1.presentation.mvp.mapper.VacancyPresentationMapper;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.utils.InternetConnection;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Grebnev on 12.11.2017.
 */
@Singleton
public class DequeVacanciesLoader implements DequeLoaderInterface {
    private static final String TAG = DequeVacanciesLoader.class.getSimpleName();
    private int itemCount = 0;

    private Application application;
    private RepositoryInterface repositoryInterface;
    private DataManagerInterface dataManagerInterface;
    private DataQueryInterface queryInterface;
    private MapVacancyMapper mapVacancyMapper;
    private DequeVacancyMapper dequeVacancyMapper;
    private DequeVacanciesInterface dequeVacanciesInterface;
    private VacancyPresentationMapper vacancyPresentationMapper;
    private CompositeDisposable disposable = new CompositeDisposable();
    private DequeVacancies dequeVacancies = new DequeVacancies();

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

    @Override
    public Observable<DequeVacancies> loadVacancies(String textSearch, int totalItemCountPresenter, int route) {
        Observable<DequeVacancies> vacanciesFromNetwork = Observable.empty();
        if (isInternetConnection()) {
            if (!dequeVacancies.getOldTextSearch().equals(textSearch)) {
                dequeVacancies = new DequeVacancies();
            }
            if (textSearch.equals("")) {
                if (dequeVacancies.getMapTime().containsKey(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD)) {
                    if (getCurrentTime() - dequeVacancies.getMapTime().get(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD)
                            > 100 * 60 * 10 && route == EndlessRecyclerConstants.SCROLL_DOWN) {
                        vacanciesFromNetwork = getDataFromNetwork(totalItemCountPresenter, route, getCurrentTime());
                    }
                } else {
                    vacanciesFromNetwork = getDataFromNetwork(totalItemCountPresenter, route, getCurrentTime());
                }
            } else {
                dequeVacancies.setOldTextSearch(textSearch);
                vacanciesFromNetwork = getDataFromSearch(textSearch, totalItemCountPresenter, route);
            }
        }

        disposable.add(queryInterface.getCountVacancies()
                .subscribeOn(Schedulers.io())
                .subscribe(this::setItemCount));

        Observable<DequeVacancies> vacanciesFromLocal = Observable.just(dequeVacancies);
        if (itemCount != 0) {
            vacanciesFromLocal = getDataFromLocal(totalItemCountPresenter, route);
        }
        return vacanciesFromNetwork.switchIfEmpty(vacanciesFromLocal);
    }

    private Observable<DequeVacancies> getDataFromNetwork(int totalItemCountPresenter, int route, long time) {
        return buildDequeVacancy(repositoryInterface.getVacanciesNetwork(EndlessRecyclerConstants.VOLUME_LOAD,
                totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD - 1)
                        .doOnNext(listVacancies -> {
                            if (totalItemCountPresenter > itemCount) {
                                saveCache(dataManagerInterface.saveData(listVacancies));
                            } else {
                                saveCache(dataManagerInterface.overwriteData(listVacancies, totalItemCountPresenter));
                            }
                            dequeVacancies.writeTime(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD,
                                    time);
                        }),
                totalItemCountPresenter,
                route);
    }

    private Observable<DequeVacancies> getDataFromLocal(int totalItemCountPresenter, int route) {
        return buildDequeVacancy(repositoryInterface.getVacanciesLocal(totalItemCountPresenter - EndlessRecyclerConstants.VOLUME_LOAD,
                totalItemCountPresenter + 1),
                totalItemCountPresenter,
                route);
    }

    private Observable<DequeVacancies> getDataFromSearch(String textSearch, int totalItemCountPresenter, int route) {
        return buildDequeVacancy(repositoryInterface.getVacanciesSearch(textSearch, EndlessRecyclerConstants.VOLUME_LOAD,
                totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD - 1),
                totalItemCountPresenter,
                route);
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

    private void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    private void saveCache(Observable data) {
        disposable.add(data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repository -> {
                    Log.d(TAG, "Overwrite data " + repository);
                }, throwable -> {
                    Log.d(TAG, "Error overwrite data " + throwable.toString());
                }));
    }

    private Observable<DequeVacancies> buildDequeVacancy(Observable<List<Vacancy>> vacancyList, int totalItemCountPresenter, int route) {
        return vacancyList
                .map(listVacancies -> vacancyPresentationMapper.transformListFromEntity(listVacancies))
                .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route));
    }
}
