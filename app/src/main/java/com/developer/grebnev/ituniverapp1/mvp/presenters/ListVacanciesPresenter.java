package com.developer.grebnev.ituniverapp1.mvp.presenters;

import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;
import com.developer.grebnev.ituniverapp1.data.DatabaseQuery;
import com.developer.grebnev.ituniverapp1.mvp.models.DequeVacancies;
import com.developer.grebnev.ituniverapp1.mvp.models.Vacancy;
import com.developer.grebnev.ituniverapp1.mvp.views.ListVacanciesView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
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
        Observable.fromCallable(new Callable<DequeVacancies>() {
            @Override
            public DequeVacancies call() throws Exception {
                DatabaseQuery query = new DatabaseQuery();
                List<Vacancy> vacancies =
                        query.getListVacancies(totalItemCountPresenter - EndlessRecyclerConstants.VOLUME_LOAD,
                                totalItemCountPresenter + 1);
                Map<Integer, List<Vacancy>> mapVacancies = new HashMap<>();
                mapVacancies.put(totalItemCountPresenter / EndlessRecyclerConstants.VOLUME_LOAD, vacancies);
                dequeVacancies.addElementIntoDeque(mapVacancies, route);
                return dequeVacancies;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    getViewState().showProgressLoad(View.VISIBLE);
                    Log.d(TAG, "doOnSubscriber");
                })
                .doFinally(() -> {
                    getViewState().showProgressLoad(View.GONE);
                    Log.d(TAG, "doFinally");
                })
                .subscribe(repositories -> {
                    getViewState().showListVacancies(repositories);
                }, Throwable::printStackTrace);
    }
}
