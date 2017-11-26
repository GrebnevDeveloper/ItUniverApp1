package com.developer.grebnev.ituniverapp1.domain.interactor;

import com.developer.grebnev.ituniverapp1.data.entity.Vacancy;
import com.developer.grebnev.ituniverapp1.data.repository.VacanciesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 26.11.2017.
 */

public class VacancyDescriptionInteractor implements DescriptionInteractorInterface {
    VacanciesRepository vacanciesRepository;

    @Inject
    public VacancyDescriptionInteractor(VacanciesRepository vacanciesRepository) {
        this.vacanciesRepository = vacanciesRepository;
    }

    @Override
    public Observable<Vacancy> getDetailVacancy(String vacancyId) {
        return vacanciesRepository.getVacancyDetail(vacancyId);
    }
}
