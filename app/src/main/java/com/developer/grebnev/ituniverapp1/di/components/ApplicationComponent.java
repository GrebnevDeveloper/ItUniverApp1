package com.developer.grebnev.ituniverapp1.di.components;

import com.developer.grebnev.ituniverapp1.di.modules.ApplicationModule;
import com.developer.grebnev.ituniverapp1.di.modules.DataModule;
import com.developer.grebnev.ituniverapp1.di.modules.DequeModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class, DequeModule.class})
public interface ApplicationComponent {
    ListVacanciesComponent listVacanciesComponent();

    DescriptionVacancyComponent descriptionVacancyComponent();
}
