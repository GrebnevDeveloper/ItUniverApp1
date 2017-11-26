package com.developer.grebnev.ituniverapp1.data.local;

import com.activeandroid.ActiveAndroid;
import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.data.entity.mapper.VacancyEntityMapper;
import com.developer.grebnev.ituniverapp1.data.local.model.VacancyLocal;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Grebnev on 04.11.2017.
 */
@Singleton
public class DataManager implements DataManagerInterface {
    VacancyEntityMapper vacancyEntityMapper;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public DataManager(VacancyEntityMapper vacancyEntityMapper) {
        this.vacancyEntityMapper = vacancyEntityMapper;
    }

    @Override
    public void saveData(List<Vacancy> vacancies) {
        compositeDisposable.add(Observable.fromCallable(() -> vacancies)
                .map(vacanciesList -> vacancyEntityMapper.transformListToLocal(vacanciesList))
                .doOnNext(vacancyList -> {
                    ActiveAndroid.beginTransaction();
                    try {
                        for (int i = 0; i < vacancyList.size(); i++) {
                            VacancyLocal vacancy = new VacancyLocal();
                            vacancy = vacancyList.get(i);
                            vacancy.save();
                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe());
    }

    @Override
    public void overwriteData(List<Vacancy> vacancies, int totalItemCount) {
        compositeDisposable.add(Observable.fromCallable(() -> vacancies)
                .map(vacanciesList -> vacancyEntityMapper.transformListToLocal(vacanciesList))
                .doOnNext(vacanciesList -> {
                    ActiveAndroid.beginTransaction();
                    try {
                        for (int i = 0; i < vacanciesList.size(); i++) {
                            VacancyLocal vacancyLocal = VacancyLocal.load(VacancyLocal.class, (totalItemCount - EndlessRecyclerConstants.VOLUME_LOAD + i + 1));
                            vacancyLocal.setAddress(vacanciesList.get(i).getAddress());
                            vacancyLocal.setCreatedAt(vacanciesList.get(i).getCreatedAt());
                            vacancyLocal.setIdVacancy(vacanciesList.get(i).getIdVacancy());
                            vacancyLocal.setName(vacanciesList.get(i).getName());
                            vacancyLocal.setSalary(vacanciesList.get(i).getSalary());
                            vacancyLocal.setEmployer(vacanciesList.get(i).getEmployer());
                            vacancyLocal.save();
                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe());
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
