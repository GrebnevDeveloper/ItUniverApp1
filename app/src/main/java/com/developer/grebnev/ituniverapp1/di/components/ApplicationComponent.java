package com.developer.grebnev.ituniverapp1.di.components;

import com.developer.grebnev.ituniverapp1.di.modules.ApplicationModule;
import com.developer.grebnev.ituniverapp1.ui.activities.MainActivity;

import dagger.Component;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
