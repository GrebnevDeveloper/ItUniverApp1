package com.developer.grebnev.ituniverapp1.di;

import com.developer.grebnev.ituniverapp1.MyApplication;
import com.developer.grebnev.ituniverapp1.di.components.ApplicationComponent;
import com.developer.grebnev.ituniverapp1.di.components.DescriptionVacancyComponent;
import com.developer.grebnev.ituniverapp1.di.components.ListVacanciesComponent;

/**
 * Created by Grebnev on 10.12.2017.
 */

public class ComponentManager {
    private static ComponentManager componentManager;

    private ApplicationComponent applicationComponent;
    private DescriptionVacancyComponent descriptionVacancyComponent;
    private ListVacanciesComponent listVacanciesComponent;

    public static ComponentManager getInstance() {
        if (componentManager == null) {
            componentManager = new ComponentManager();
        }
        return componentManager;
    }

    private ComponentManager() {}

    public void initApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = MyApplication.getApplicationComponent();
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public DescriptionVacancyComponent getDescriptionVacancyComponent() {
        if (descriptionVacancyComponent == null) {
            descriptionVacancyComponent = getApplicationComponent().descriptionVacancyComponent();
        }
        return descriptionVacancyComponent;
    }

    public void clearDescriptionVacancyComponent() {
        descriptionVacancyComponent = null;
    }

    public ListVacanciesComponent getListVacanciesComponent() {
        if (listVacanciesComponent == null) {
            listVacanciesComponent = getApplicationComponent().listVacanciesComponent();
        }
        return listVacanciesComponent;
    }

    public void clearListVacancyComponent() {
        listVacanciesComponent = null;
    }
}
