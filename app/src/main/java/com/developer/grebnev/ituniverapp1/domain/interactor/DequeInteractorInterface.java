package com.developer.grebnev.ituniverapp1.domain.interactor;


import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Grebnev on 11.11.2017.
 */

public interface DequeInteractorInterface {
    Observable<DequeVacancies> getDequeVacancies(int totalItemCountPresenter, int route);

    boolean isInternetConnection();

    int getTotalItemCountPresenter();

    void setTotalItemCountPresenter(int countPresenter);

    DequeVacancies getDequeVacancies();

    void setDequeVacancies(DequeVacancies dequeVacancies);

    int getScrollConstants(int totalItemCount);

    String getTextSearch();

    void setTextSearch(String textSearch);

    CompositeDisposable getCompositeDisposableData();
}
