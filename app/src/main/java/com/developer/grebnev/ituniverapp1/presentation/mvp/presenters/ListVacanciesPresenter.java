package com.developer.grebnev.ituniverapp1.presentation.mvp.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.developer.grebnev.ituniverapp1.MyApplication;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;
import com.developer.grebnev.ituniverapp1.domain.interactor.DequeVacanciesInteractor;
import com.developer.grebnev.ituniverapp1.presentation.mvp.view.ListVacanciesView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Grebnev on 07.11.2017.
 */

@InjectViewState
public class ListVacanciesPresenter extends MvpPresenter<ListVacanciesView> implements ListPresenterInterface {
    private static final String TAG = ListVacanciesPresenter.class.getSimpleName();

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    DequeVacanciesInteractor dequeVacanciesInteractor;

    public ListVacanciesPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void loadNextDataFromDatabase(int totalItemCount) {
        int scroll = dequeVacanciesInteractor.getScrollConstants(totalItemCount);
        if (scroll == EndlessRecyclerConstants.SCROLL_NO && getTextSearch().equals("")) {
            getViewState().showListVacancies(dequeVacanciesInteractor.getDequeVacancies());
        } else {
            loadData(scroll);
        }
    }

    public void loadData(final int route) {
        if (!dequeVacanciesInteractor.isInternetConnection()) {
            getViewState().showErrorConnection();
        }
        disposable.add(dequeVacanciesInteractor.getDequeVacancies(dequeVacanciesInteractor.getTotalItemCountPresenter(), route)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    getViewState().showProgressLoad(View.VISIBLE);
                })
                .doFinally(() -> {
                    getViewState().showProgressLoad(View.INVISIBLE);
                })
                .subscribe(repository -> {
                    dequeVacanciesInteractor.setDequeVacancies(repository);
                    getViewState().showListVacancies(repository);
                }));
    }

    @Override
    public int getTotalItemCountPresenter() {
        return dequeVacanciesInteractor.getTotalItemCountPresenter();
    }

    @Override
    public void setTotalItemCountPresenter(int countPresenter) {
        dequeVacanciesInteractor.setTotalItemCountPresenter(countPresenter);
    }

    @Override
    public DequeVacancies getDequeVacancies() {
        return dequeVacanciesInteractor.getDequeVacancies();
    }

    @Override
    public String getTextSearch() {
        return dequeVacanciesInteractor.getTextSearch();
    }

    @Override
    public void setTextSearch(String textSearch) {
        dequeVacanciesInteractor.setTextSearch(textSearch);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.addAll(dequeVacanciesInteractor.getCompositeDisposableData());
        if (disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}