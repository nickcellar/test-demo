package com.nicholasworkshop.tinklabstest;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Module
public class MainModule {

    private final Application application;

    MainModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context context() {
        return application;
    }
}
