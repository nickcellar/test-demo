package com.nicholasworkshop.tinklabstest;

import android.app.Application;

import com.nicholasworkshop.tinklabstest.external.ads.AdsServiceModule;
import com.nicholasworkshop.tinklabstest.external.content.ContentServiceModule;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

public class MainApplication extends Application {

    private MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .contentServiceModule(new ContentServiceModule())
                .adsServiceModule(new AdsServiceModule())
                .build();
    }

    public MainComponent getComponent() {
        return component;
    }
}
