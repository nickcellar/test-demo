package com.nicholasworkshop.tinklabstest.external.content;

import android.content.Context;

import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Module
public class ContentServiceModule {

    private static final String BASE_URL = "https://tinklabs-test.firebaseio.com/content/";

    @Provides
    @Singleton
    ContentService contentService() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ContentService.class);
    }
}
