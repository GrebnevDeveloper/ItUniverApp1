package com.developer.grebnev.ituniverapp1.di.components;

import com.developer.grebnev.ituniverapp1.di.modules.DescriptionVacancyModule;
import com.developer.grebnev.ituniverapp1.di.scopes.DescriptionVacancyScope;
import com.developer.grebnev.ituniverapp1.presentation.ui.fragments.VacancyDescriptionFragment;

import dagger.Subcomponent;

/**
 * Created by Grebnev on 10.12.2017.
 */
@DescriptionVacancyScope
@Subcomponent(modules = DescriptionVacancyModule.class)
public interface DescriptionVacancyComponent {
    void inject(VacancyDescriptionFragment vacancyDescriptionFragment);
}
