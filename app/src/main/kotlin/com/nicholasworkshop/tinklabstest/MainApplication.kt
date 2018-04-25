package com.nicholasworkshop.tinklabstest

import android.app.Application

import com.nicholasworkshop.tinklabstest.external.ads.AdsServiceModule
import com.nicholasworkshop.tinklabstest.external.content.ContentServiceModule

class MainApplication : Application() {

    lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .contentServiceModule(ContentServiceModule())
                .adsServiceModule(AdsServiceModule())
                .build()
    }
}
