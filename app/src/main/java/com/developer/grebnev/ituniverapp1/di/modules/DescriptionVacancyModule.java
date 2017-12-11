package com.developer.grebnev.ituniverapp1.di.modules;

import com.developer.grebnev.ituniverapp1.di.scopes.DescriptionVacacnyScope;
import com.developer.grebnev.ituniverapp1.domain.interactor.DescriptionInteractorInterface;
import com.developer.grebnev.ituniverapp1.domain.interactor.VacancyDescriptionInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grebnev on 10.12.2017.
 */
@Module
public class DescriptionVacancyModule {

    @DescriptionVacacnyScope
    @Provides
    public DescriptionInteractorInterface provideDequeInteractor(VacancyDescriptionInteractor descriptionInteractor) {
        return descriptionInteractor;
    }
}
