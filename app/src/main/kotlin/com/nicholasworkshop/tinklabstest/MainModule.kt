package com.nicholasworkshop.tinklabstest

import android.app.Application
import android.content.Context
import android.view.LayoutInflater

import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
internal class MainModule(
        private val application: Application) {

    @Provides
    @Singleton
    fun context(): Context {
        return application
    }

    @Provides
    @Singleton
    fun requestManager(context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    @Singleton
    fun layoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }
}
