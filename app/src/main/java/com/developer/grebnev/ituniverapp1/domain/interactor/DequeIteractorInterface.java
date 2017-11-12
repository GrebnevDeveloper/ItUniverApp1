package com.developer.grebnev.ituniverapp1.domain.interactor;


import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 11.11.2017.
 */

public interface DequeIteractorInterface {
    Observable<DequeVacancies> getDequeVacancies(int totalItemCountPresenter, int route);
    boolean isInternetConnection();
    int getTotalItemCountPresenter();
    DequeVacancies getDequeVacancies();
    void setDequeVacancies(DequeVacancies dequeVacancies);
    int getScrollConstants(int totalItemCount);
}
