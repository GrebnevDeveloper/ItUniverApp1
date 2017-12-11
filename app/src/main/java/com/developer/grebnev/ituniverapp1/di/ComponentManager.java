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
        return applicationComponent.descriptionVacancyComponent();
    }

    public ListVacanciesComponent getListVacanciesComponent() {
        return applicationComponent.listVacanciesComponent();
    }

}
