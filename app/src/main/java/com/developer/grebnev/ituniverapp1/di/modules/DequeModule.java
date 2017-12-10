package com.developer.grebnev.ituniverapp1.di.modules;

import android.app.Application;

import com.developer.grebnev.ituniverapp1.data.local.DataManagerInterface;
import com.developer.grebnev.ituniverapp1.data.local.DataQueryInterface;
import com.developer.grebnev.ituniverapp1.data.repository.RepositoryInterface;
import com.developer.grebnev.ituniverapp1.data.repository.VacanciesRepository;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeLoaderInterface;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacancies;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacanciesInterface;
import com.developer.grebnev.ituniverapp1.domain.deque.DequeVacanciesLoader;
import com.developer.grebnev.ituniverapp1.domain.mapper.DequeVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.MapVacancyMapper;
import com.developer.grebnev.ituniverapp1.domain.mapper.VacancyPresentationMapper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grebnev on 10.12.2017.
 */
@Module
public class DequeModule {
    @Provides
    public DequeLoaderInterface provideDequeLoader(Application application,
                                                          RepositoryInterface repositoryInterface,
                                                          DataManagerInterface dataManagerInterface,
                                                          DataQueryInterface queryInterface,
                                                          MapVacancyMapper mapVacancyMapper,
                                                          DequeVacancyMapper dequeVacancyMapper,
                                                          DequeVacanciesInterface dequeVacanciesInterface,
                                                          VacancyPresentationMapper vacancyPresentationMapper) {
        return new DequeVacanciesLoader(application,
                repositoryInterface,
                dataManagerInterface,
                queryInterface,
                mapVacancyMapper,
                dequeVacancyMapper,
                dequeVacanciesInterface,
                vacancyPresentationMapper);
    }

    @Provides
    public RepositoryInterface provideRepository(VacanciesRepository vacanciesRepository) {
        return vacanciesRepository;
    }
    @Provides
    public static DequeVacanciesInterface provideDequeVacancies(DequeVacancies dequeVacancies) {
        return dequeVacancies;
    }
}
