package com.developer.grebnev.ituniverapp1.presentation.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;
import com.developer.grebnev.ituniverapp1.domain.interactor.DequeInteractorInterface;
import com.developer.grebnev.ituniverapp1.presentation.mvp.view.ListVacanciesView;
import com.developer.grebnev.ituniverapp1.utils.EndlessRecyclerConstants;

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

    private DequeInteractorInterface dequeInteractorInterface;

    @Inject
    public ListVacanciesPresenter(DequeInteractorInterface dequeInteractorInterface) {
        this.dequeInteractorInterface = dequeInteractorInterface;
    }

    public void loadNextDataFromDatabase(int totalItemCount) {
        int scroll = dequeInteractorInterface.getScrollConstants(totalItemCount);
        if (scroll == EndlessRecyclerConstants.SCROLL_NO) {
            getViewState().showListVacancies(dequeInteractorInterface.getDequeVacancies());
        } else {
            loadData(scroll);
        }
    }

    public void loadData(final int route) {
        if (!dequeInteractorInterface.isInternetConnection()) {
            getViewState().showErrorConnection();
        }
        disposable.add(dequeInteractorInterface.getDequeVacancies(dequeInteractorInterface.getTotalItemCountPresenter(), route)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    getViewState().showProgressLoad(true);
                })
                .doFinally(() -> {
                    getViewState().showProgressLoad(false);
                })
                .subscribe(repository -> {
                    dequeInteractorInterface.setDequeVacancies(repository);
                    getViewState().showListVacancies(repository);
                }));
    }

    @Override
    public int getTotalItemCountPresenter() {
        return dequeInteractorInterface.getTotalItemCountPresenter();
    }

    @Override
    public void setTotalItemCountPresenter(int countPresenter) {
        dequeInteractorInterface.setTotalItemCountPresenter(countPresenter);
    }

    @Override
    public DequeVacancies getDequeVacancies() {
        return dequeInteractorInterface.getDequeVacancies();
    }

    @Override
    public String getTextSearch() {
        return dequeInteractorInterface.getTextSearch();
    }

    @Override
    public void setTextSearch(String textSearch) {
        dequeInteractorInterface.setTextSearch(textSearch);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.addAll(dequeInteractorInterface.getCompositeDisposableData());
        if (disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}