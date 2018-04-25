package com.nicholasworkshop.tinklabstest.fragment.guide

import com.nicholasworkshop.tinklabstest.external.ads.AdsServiceManager
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad
import com.nicholasworkshop.tinklabstest.external.content.ContentServiceManager
import com.nicholasworkshop.tinklabstest.external.content.model.Story
import io.reactivex.Observable
import javax.inject.Inject

class GuideModel @Inject internal constructor(
        private val adsServiceManager: AdsServiceManager,
        private val contentServiceManager: ContentServiceManager) {

    internal fun storyList(@GuideType guideType: Int, count: Int): Observable<List<Story>>? {
        when (guideType) {
            TYPE_CITY -> return contentServiceManager.getCityGuide(count)
            TYPE_EAT -> return contentServiceManager.getEatGuide(count)
            TYPE_SHOP -> return contentServiceManager.getShopGuide(count)
        }
        return null
    }

    internal fun ads(): Observable<List<Ad>> {
        return adsServiceManager.get()
    }
}
