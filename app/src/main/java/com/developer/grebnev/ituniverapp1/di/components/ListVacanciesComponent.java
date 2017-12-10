package com.developer.grebnev.ituniverapp1.di.components;

import com.developer.grebnev.ituniverapp1.di.modules.ListVacanciesModule;
import com.developer.grebnev.ituniverapp1.presentation.ui.fragments.ListVacanciesFragment;

import dagger.Subcomponent;

/**
 * Created by Grebnev on 10.12.2017.
 */
@Subcomponent(modules = ListVacanciesModule.class)
public interface ListVacanciesComponent {
    void inject(ListVacanciesFragment listVacanciesFragment);
}
