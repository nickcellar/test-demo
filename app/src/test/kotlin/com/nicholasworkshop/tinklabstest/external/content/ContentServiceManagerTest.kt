package com.nicholasworkshop.tinklabstest.external.content

import com.nhaarman.mockito_kotlin.whenever
import com.nicholasworkshop.tinklabstest.TestUtilities.initRxSchedulers
import com.nicholasworkshop.tinklabstest.external.content.model.Story
import com.nicholasworkshop.tinklabstest.external.content.model.StoryFaker.fakeStoryList
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class ContentServiceManagerTest {

    @Mock private lateinit var contentService: ContentService

    private lateinit var testObserver: TestObserver<List<Story>>
    private lateinit var contentServiceManager: ContentServiceManager

    @Before
    fun setUp() {
        initMocks(this)
        initRxSchedulers()
        testObserver = TestObserver.create()
        contentServiceManager = ContentServiceManager(contentService)
    }

    @Test
    fun getCityGuide() {
        val count = 5
        val storyList = fakeStoryList(10)
        whenever(contentService.cityGuide()).thenReturn(Observable.just(storyList))
        contentServiceManager.getCityGuide(count).subscribe(testObserver)
        testObserver.assertValue { returnedStoryList ->
            assertEquals(count.toLong(), returnedStoryList.size.toLong())
            assertEquals(storyList[0], returnedStoryList[0])
            assertEquals(storyList[count - 1], returnedStoryList[count - 1])
            true
        }
    }

    @Test
    fun getEatGuide() {
        val count = 5
        val storyList = fakeStoryList(10)
        whenever(contentService.eatGuide()).thenReturn(Observable.just(storyList))
        contentServiceManager.getEatGuide(count).subscribe(testObserver)
        testObserver.assertValue { returnedStoryList ->
            assertEquals(count.toLong(), returnedStoryList.size.toLong())
            assertEquals(storyList[0], returnedStoryList[0])
            assertEquals(storyList[count - 1], returnedStoryList[count - 1])
            true
        }
    }

    @Test
    fun getShopGuide() {
        val count = 5
        val storyList = fakeStoryList(10)
        whenever(contentService.shopGuide()).thenReturn(Observable.just(storyList))
        contentServiceManager.getShopGuide(count).subscribe(testObserver)
        testObserver.assertValue { returnedStoryList ->
            assertEquals(count.toLong(), returnedStoryList.size.toLong())
            assertEquals(storyList[0], returnedStoryList[0])
            assertEquals(storyList[count - 1], returnedStoryList[count - 1])
            true
        }
    }
}