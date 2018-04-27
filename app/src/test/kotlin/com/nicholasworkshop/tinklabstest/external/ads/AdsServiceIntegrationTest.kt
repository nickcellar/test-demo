package com.nicholasworkshop.tinklabstest.external.ads

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AdsServiceIntegrationTest {

    private lateinit var adsService: AdsService

    @Before
    fun setUp() {
        val module = AdsServiceModule()
        adsService = module.adsService()
    }

    @Test
    fun get() {
        val adList = adsService.get().blockingFirst()
        assertEquals(2, adList.size.toLong())
    }
}