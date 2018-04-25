package com.nicholasworkshop.tinklabstest.external.content

import com.nicholasworkshop.tinklabstest.external.content.model.Story
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentServiceManager
@Inject
internal constructor(private val contentService: ContentService) {

    fun getCityGuide(count: Int): Observable<List<Story>> {
        return contentService
                .cityGuide()
                .cache()
                .map { it.subList(0, Math.min(it.size, count)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getEatGuide(count: Int): Observable<List<Story>> {
        return contentService
                .eatGuide()
                .cache()
                .map { it.subList(0, Math.min(it.size, count)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getShopGuide(count: Int): Observable<List<Story>> {
        return contentService
                .shopGuide()
                .cache()
                .map { it.subList(0, Math.min(it.size, count)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
