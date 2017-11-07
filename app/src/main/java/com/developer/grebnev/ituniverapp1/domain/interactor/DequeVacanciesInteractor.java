package com.developer.grebnev.ituniverapp1.domain.interactor;

import com.developer.grebnev.ituniverapp1.domain.repository.DequeVacancies;
import com.developer.grebnev.ituniverapp1.domain.repository.DequeVacanciesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 07.11.2017.
 */

public class DequeVacanciesInteractor {
    DequeVacanciesRepository dequeVacanciesRepository;

    @Inject
    public DequeVacanciesInteractor(DequeVacanciesRepository dequeVacanciesRepository) {
        this.dequeVacanciesRepository = dequeVacanciesRepository;
    }

    public Observable<DequeVacancies> getDequeVacancies(int totalItemCountPresenter, int route) {
        return dequeVacanciesRepository.loadVacancies(totalItemCountPresenter, route);
    }
}
