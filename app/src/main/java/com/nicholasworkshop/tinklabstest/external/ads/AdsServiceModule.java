package com.nicholasworkshop.tinklabstest.external.ads;

import com.nicholasworkshop.tinklabstest.external.content.ContentService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Module
public class AdsServiceModule {

    private static final String BASE_URL = "https://tinklabs-test.firebaseio.com/ads/";

    @Provides
    @Singleton
    AdsService adsService() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(AdsService.class);
    }
}
