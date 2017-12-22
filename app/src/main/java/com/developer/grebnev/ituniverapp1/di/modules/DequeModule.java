package com.developer.grebnev.ituniverapp1.di.modules;

import com.developer.grebnev.ituniverapp1.data.repository.RepositoryInterface;
import com.developer.grebnev.ituniverapp1.data.repository.VacanciesRepository;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeLoaderInterface;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacanciesInterface;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacanciesLoader;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grebnev on 10.12.2017.
 */
@Module
public class DequeModule {
    @Provides
    public static DequeVacanciesInterface provideDequeVacancies(DequeVacancies dequeVacancies) {
        return dequeVacancies;
    }

    @Provides
    public RepositoryInterface provideRepository(VacanciesRepository vacanciesRepository) {
        return vacanciesRepository;
    }

    @Provides
    public DequeLoaderInterface provideDequeLoader(DequeVacanciesLoader dequeVacanciesLoader) {
        return dequeVacanciesLoader;
    }
}
