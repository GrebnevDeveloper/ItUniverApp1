package com.developer.grebnev.ituniverapp1.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity activity() {
        return this.activity;
    }
}
