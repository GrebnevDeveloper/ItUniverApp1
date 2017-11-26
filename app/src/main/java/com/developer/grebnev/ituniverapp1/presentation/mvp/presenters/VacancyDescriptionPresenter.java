package com.developer.grebnev.ituniverapp1.presentation.mvp.presenters;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.developer.grebnev.ituniverapp1.MyApplication;
import com.developer.grebnev.ituniverapp1.domain.interactor.VacancyDescriptionInteractor;
import com.developer.grebnev.ituniverapp1.presentation.mvp.view.VacancyDescriptionView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Grebnev on 25.11.2017.
 */

@InjectViewState
public class VacancyDescriptionPresenter extends MvpPresenter<VacancyDescriptionView> {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    VacancyDescriptionInteractor vacancyDescriptionInteractor;

    public VacancyDescriptionPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void loadVacancyDescription(String vacancyId) {
        disposable.add(vacancyDescriptionInteractor.getDetailVacancy(vacancyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    getViewState().showProgressLoad(View.VISIBLE);
                })
                .doFinally(() -> {
                    getViewState().showProgressLoad(View.INVISIBLE);
                })
                .subscribe(repository -> {
                    getViewState().showFullData(repository);
                }));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
