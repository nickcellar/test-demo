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

    internal fun storyList(guideType: GuideType, count: Int): Observable<List<Story>>? {
        return when (guideType) {
            GuideType.CITY -> contentServiceManager.getCityGuide(count)
            GuideType.EAT -> contentServiceManager.getEatGuide(count)
            GuideType.SHOP -> contentServiceManager.getShopGuide(count)
        }
    }

    internal fun ads(): Observable<List<Ad>> {
        return adsServiceManager.get()
    }
}
