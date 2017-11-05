package com.developer.grebnev.ituniverapp1.di.components;

import android.app.Activity;

import com.developer.grebnev.ituniverapp1.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Grebnev on 05.11.2017.
 */
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
