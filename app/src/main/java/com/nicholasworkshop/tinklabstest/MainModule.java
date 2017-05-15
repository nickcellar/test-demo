package com.nicholasworkshop.tinklabstest;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Module
class MainModule {

    private final Application application;

    MainModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context context() {
        return application;
    }

    @Provides
    @Singleton
    RequestManager requestManager(Context context) {
        return Glide.with(context);
    }

    @Provides
    @Singleton
    LayoutInflater layoutInflater(Context context) {
        return LayoutInflater.from(context);
    }
}
