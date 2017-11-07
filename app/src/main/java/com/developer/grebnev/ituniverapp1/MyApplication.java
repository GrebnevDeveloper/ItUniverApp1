package com.developer.grebnev.ituniverapp1;

import android.app.Application;

import com.developer.grebnev.ituniverapp1.di.components.ApplicationComponent;
import com.developer.grebnev.ituniverapp1.di.components.DaggerApplicationComponent;
import com.developer.grebnev.ituniverapp1.di.modules.ApplicationModule;

/**
 * Created by Grebnev on 05.11.2017.
 */

public class MyApplication extends Application {
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
