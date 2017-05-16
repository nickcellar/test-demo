package com.nicholasworkshop.tinklabstest.external.ads;

import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Singleton
public class AdsServiceManager {

    private final AdsService adsService;

    @Inject
    AdsServiceManager(AdsService adsService) {
        this.adsService = adsService;
    }

    public Observable<List<Ad>> get() {
        return adsService
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
