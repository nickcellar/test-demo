package com.nicholasworkshop.tinklabstest.external.ads

import com.nicholasworkshop.tinklabstest.external.ads.model.Ad
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdsServiceManager
@Inject
internal constructor(private val adsService: AdsService) {

    fun get(): Observable<List<Ad>> {
        return adsService
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
