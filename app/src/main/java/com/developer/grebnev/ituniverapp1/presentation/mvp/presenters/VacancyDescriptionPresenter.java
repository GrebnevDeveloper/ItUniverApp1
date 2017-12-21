package com.developer.grebnev.ituniverapp1.presentation.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeLoaderInterface;
import com.developer.grebnev.ituniverapp1.domain.interactor.DescriptionInteractorInterface;
import com.developer.grebnev.ituniverapp1.presentation.mvp.mapper.VacancyPresentationMapper;
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

    private DescriptionInteractorInterface descriptionInteractorInterface;

    private DequeLoaderInterface dequeLoaderInterface;

    private VacancyPresentationMapper vacancyPresentationMapper;

    @Inject
    public VacancyDescriptionPresenter(DescriptionInteractorInterface descriptionInteractorInterface,
                                       DequeLoaderInterface dequeLoaderInterface,
                                       VacancyPresentationMapper vacancyPresentationMapper) {
        this.descriptionInteractorInterface = descriptionInteractorInterface;
        this.dequeLoaderInterface = dequeLoaderInterface;
        this.vacancyPresentationMapper = vacancyPresentationMapper;
    }

    public void loadVacancyDescription(String vacancyId) {
        if (!dequeLoaderInterface.isInternetConnection()) {
            getViewState().showErrorConnection();
        } else {
            disposable.add(descriptionInteractorInterface.getDetailVacancy(vacancyId)
                    .map(vacancy -> vacancyPresentationMapper.transformFromEntity(vacancy))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> {
                        getViewState().showProgressLoad(true);
                    })
                    .doFinally(() -> {
                        getViewState().showProgressLoad(false);
                    })
                    .subscribe(repository -> {
                        getViewState().showFullData(repository);
                    }));
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
