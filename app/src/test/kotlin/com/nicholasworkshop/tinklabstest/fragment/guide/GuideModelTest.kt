package com.nicholasworkshop.tinklabstest.fragment.guide

import com.nicholasworkshop.tinklabstest.external.ads.AdsServiceManager
import com.nicholasworkshop.tinklabstest.external.content.ContentServiceManager

import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

class GuideModelTest {

    @Mock private lateinit var adsServiceManager: AdsServiceManager
    @Mock private lateinit var contentServiceManager: ContentServiceManager

    private lateinit var guideModel: GuideModel

    @Before
    fun setUp() {
        initMocks(this)
        guideModel = GuideModel(adsServiceManager, contentServiceManager)
    }

    @Test
    fun storyList_getCityGuide() {
        guideModel.storyList(GuideType.CITY, 10)
        verify<ContentServiceManager>(contentServiceManager).getCityGuide(10)
    }

    @Test
    fun storyList_getShopGuide() {
        guideModel.storyList(GuideType.SHOP, 10)
        verify<ContentServiceManager>(contentServiceManager).getShopGuide(10)
    }

    @Test
    fun storyList_getEatGuide() {
        guideModel.storyList(GuideType.EAT, 10)
        verify<ContentServiceManager>(contentServiceManager).getEatGuide(10)
    }

    @Test
    fun ads() {
        guideModel.ads()
        verify<AdsServiceManager>(adsServiceManager).get()
    }
}