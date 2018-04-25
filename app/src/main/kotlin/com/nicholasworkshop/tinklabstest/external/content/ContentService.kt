package com.nicholasworkshop.tinklabstest.external.content

import com.nicholasworkshop.tinklabstest.external.content.model.Story

import io.reactivex.Observable
import retrofit2.http.GET

internal interface ContentService {

    @GET("city.json")
    fun cityGuide(): Observable<List<Story>>

    @GET("shop.json")
    fun shopGuide(): Observable<List<Story>>

    @GET("eat.json")
    fun eatGuide(): Observable<List<Story>>
}
