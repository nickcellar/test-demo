package com.nicholasworkshop.tinklabstest.external.ads

import com.nicholasworkshop.tinklabstest.external.ads.model.Ad

import io.reactivex.Observable
import retrofit2.http.GET

internal interface AdsService {

    @GET("data.json")
    fun get(): Observable<List<Ad>>
}
