package com.nicholasworkshop.tinklabstest.external.content

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContentServiceIntegrationTest {

    private lateinit var contentService: ContentService

    @Before
    fun setUp() {
        val module = ContentServiceModule()
        contentService = module.contentService()
    }

    @Test
    fun getCityGuide() {
        val storyList = contentService.cityGuide().blockingFirst()
        assertEquals(102, storyList.size.toLong())
    }

    @Test
    fun getShopGuide() {
        val storyList = contentService.shopGuide().blockingFirst()
        assertEquals(100, storyList.size.toLong())
    }

    @Test
    fun getEatGuide() {
        val storyList = contentService.eatGuide().blockingFirst()
        assertEquals(100, storyList.size.toLong())
    }
}