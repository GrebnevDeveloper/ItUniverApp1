package com.developer.grebnev.ituniverapp1.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.data.entity.mapper.VacancyJsonMapper;
import com.developer.grebnev.ituniverapp1.data.local.DataManager;
import com.developer.grebnev.ituniverapp1.data.local.DatabaseQuery;
import com.developer.grebnev.ituniverapp1.data.network.RequestInterface;
import com.developer.grebnev.ituniverapp1.data.network.RetrofitManager;
import com.developer.grebnev.ituniverapp1.domain.interactor.DequeVacancies;
import com.developer.grebnev.ituniverapp1.domain.mapper.DequeVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.MapVacancyMapper;
import com.developer.grebnev.ituniverapp1.mvp.views.ListVacanciesView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Grebnev on 20.10.2017.
 */

@InjectViewState
public class ListVacanciesPresenter extends MvpPresenter<ListVacanciesView> {
    private static final String TAG = ListVacanciesPresenter.class.getSimpleName();
    private DequeVacancies dequeVacancies = new DequeVacancies();
    private int totalItemCountPresenter = EndlessRecyclerConstants.VOLUME_LOAD;

    public int getTotalItemCountPresenter() {
        return totalItemCountPresenter;
    }

    public DequeVacancies getDequeVacancies() {
        return dequeVacancies;
    }

    public void loadNextDataFromDatabase(int totalItemCount) {
        if (totalItemCountPresenter == totalItemCount) {
            if (!dequeVacancies.getDequeVacancies().isEmpty()) {
                getViewState().showListVacancies(dequeVacancies);
            } else {
                loadData(EndlessRecyclerConstants.SCROLL_DOWN);
            }
        } else {
            if (totalItemCountPresenter > totalItemCount) {
                totalItemCountPresenter = totalItemCount;
                loadData(EndlessRecyclerConstants.SCROLL_UP);
            } else {
                totalItemCountPresenter = totalItemCount;
                loadData(EndlessRecyclerConstants.SCROLL_DOWN);
            }
        }
    }

    private void loadData(final int route) {
        VacancyJsonMapper vacancyJsonMapper = new VacancyJsonMapper();
        DataManager dataManager = new DataManager();
        DatabaseQuery query = new DatabaseQuery();
        MapVacancyMapper mapVacancyMapper = new MapVacancyMapper();
        DequeVacancyMapper dequeVacancyMapper = new DequeVacancyMapper();
        if (query.getCountVacancies() >= totalItemCountPresenter) {
            query.getListVacancies(totalItemCountPresenter - EndlessRecyclerConstants.VOLUME_LOAD, totalItemCountPresenter)
                    .subscribeOn(Schedulers.io())
                    .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                    .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(repositories -> {
                        getViewState().showListVacancies(repositories);
                    });
        }
        RequestInterface request = RetrofitManager.getRequestInterface();
        request.getVacancies(100,
                totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD - 1)
                .subscribeOn(Schedulers.io())
                .map(pageVacancy -> vacancyJsonMapper.transformJsonToVacancy(pageVacancy))
                .doOnNext(listVacancies -> dataManager.saveData(listVacancies))
                .map(listVacancies -> mapVacancyMapper.createMapVacancies(totalItemCountPresenter, listVacancies))
                .map(mapVacancy -> dequeVacancyMapper.createDequeVacancy(dequeVacancies, mapVacancy, route))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositories -> {
                    getViewState().showListVacancies(repositories);
                });
    }
}
