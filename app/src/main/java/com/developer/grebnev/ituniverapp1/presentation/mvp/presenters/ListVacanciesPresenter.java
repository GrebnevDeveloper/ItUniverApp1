package com.developer.grebnev.ituniverapp1.presentation.mvp.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.developer.grebnev.ituniverapp1.MyApplication;
import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.domain.interactor.DequeVacanciesInteractor;
import com.developer.grebnev.ituniverapp1.domain.repository.DequeVacancies;
import com.developer.grebnev.ituniverapp1.presentation.mvp.view.ListVacanciesView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Grebnev on 07.11.2017.
 */

@InjectViewState
public class ListVacanciesPresenter extends MvpPresenter<ListVacanciesView> {
    private static final String TAG = ListVacanciesPresenter.class.getSimpleName();
    private DequeVacancies dequeVacancies = new DequeVacancies();
    private int totalItemCountPresenter = EndlessRecyclerConstants.VOLUME_LOAD;
    @Inject
    DequeVacanciesInteractor dequeVacanciesInteractor;

    public ListVacanciesPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

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
        dequeVacanciesInteractor.getDequeVacancies(totalItemCountPresenter, route)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    getViewState().showProgressLoad(View.VISIBLE);
                })
                .doFinally(() -> {
                    getViewState().showProgressLoad(View.INVISIBLE);
                })
                .subscribe(repository -> {
                    dequeVacancies = repository;
                    getViewState().showListVacancies(repository);
                });
    }
}