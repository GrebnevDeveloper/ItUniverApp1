package com.developer.grebnev.ituniverapp1.di.components;

import com.developer.grebnev.ituniverapp1.di.modules.ApplicationModule;
import com.developer.grebnev.ituniverapp1.di.modules.VacancyModule;
import com.developer.grebnev.ituniverapp1.presentation.mvp.presenters.ListVacanciesPresenter;
import com.developer.grebnev.ituniverapp1.presentation.mvp.presenters.VacancyDescriptionPresenter;
import com.developer.grebnev.ituniverapp1.presentation.ui.activities.MainActivity;
import com.developer.grebnev.ituniverapp1.presentation.ui.fragments.ListVacanciesFragment;
import com.developer.grebnev.ituniverapp1.presentation.ui.fragments.VacancyDescriptionFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class, VacancyModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(ListVacanciesFragment listVacanciesFragment);
    void inject(VacancyDescriptionFragment vacancyDescriptionFragment);
    void inject(ListVacanciesPresenter listVacanciesPresenter);
    void inject(VacancyDescriptionPresenter vacancyDescriptionPresenter);
}
