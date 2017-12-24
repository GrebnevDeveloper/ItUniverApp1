package com.developer.grebnev.ituniverapp1.domain.interactor;

import com.developer.grebnev.ituniverapp1.data.repository.RepositoryInterface;
import com.developer.grebnev.ituniverapp1.di.scopes.DescriptionVacancyScope;
import com.developer.grebnev.ituniverapp1.domain.entity.Vacancy;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Grebnev on 26.11.2017.
 */
@DescriptionVacancyScope
public class VacancyDescriptionInteractor implements DescriptionInteractorInterface {
    private RepositoryInterface repositoryInterface;

    @Inject
    public VacancyDescriptionInteractor(RepositoryInterface repositoryInterface) {
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    public Observable<Vacancy> getDetailVacancy(String vacancyId) {
        return repositoryInterface.getVacancyDetail(vacancyId);
    }
}
