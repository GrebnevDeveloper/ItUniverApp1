package com.developer.grebnev.ituniverapp1.di.components;

import com.developer.grebnev.ituniverapp1.di.modules.ActivityModule;
import com.developer.grebnev.ituniverapp1.di.modules.VacancyModule;
import com.developer.grebnev.ituniverapp1.ui.fragments.ListVacanciesFragment;

import dagger.Component;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Component(dependencies = ApplicationComponent.class , modules = {ActivityModule.class, VacancyModule.class})
public interface VacancyComponent extends ActivityComponent {
    void inject(ListVacanciesFragment listVacanciesFragment);
//    void inject(VacancyDescriptionFragment vacancyDescriptionFragment);
}
