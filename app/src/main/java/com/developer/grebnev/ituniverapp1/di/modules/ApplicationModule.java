package com.developer.grebnev.ituniverapp1.di.modules;

import android.app.Application;

import com.developer.grebnev.ituniverapp1.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Module
public class ApplicationModule {
    App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    public Application providesApplication() {
        return application;
    }
}
