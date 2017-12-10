package com.developer.grebnev.ituniverapp1.domain.deque;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Grebnev on 09.12.2017.
 */

public interface DequeLoaderInterface {
    Observable<DequeVacancies> loadVacancies(String textSearch, int totalItemCountPresenter, int route);
    boolean isInternetConnection();
    CompositeDisposable getCompositeDisposableData();
}
