package com.developer.grebnev.ituniverapp1.di.modules;

import com.developer.grebnev.ituniverapp1.domain.interactor.DequeInteractorInterface;
import com.developer.grebnev.ituniverapp1.domain.interactor.DequeVacanciesInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grebnev on 09.12.2017.
 */
@Module
public class ListVacanciesModule {
    @Provides
    public static DequeInteractorInterface provideDequeInteractor(DequeVacanciesInteractor dequeVacanciesInteractor) {
        return dequeVacanciesInteractor;
    }
}
