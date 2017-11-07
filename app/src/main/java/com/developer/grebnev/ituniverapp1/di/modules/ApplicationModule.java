package com.developer.grebnev.ituniverapp1.di.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Module
public class ApplicationModule {
    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application providesApplication() {
        return application;
    }
}
