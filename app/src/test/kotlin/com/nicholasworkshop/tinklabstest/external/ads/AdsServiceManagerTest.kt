package com.nicholasworkshop.tinklabstest.external.ads

import com.nhaarman.mockito_kotlin.whenever
import com.nicholasworkshop.tinklabstest.TestUtilities.initRxSchedulers
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad
import com.nicholasworkshop.tinklabstest.external.ads.model.AdFaker.fakeAdList
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class AdsServiceManagerTest {

    @Mock private lateinit var adsService: AdsService

    private lateinit var testObserver: TestObserver<List<Ad>>
    private lateinit var adsServiceManager: AdsServiceManager

    @Before
    fun setUp() {
        initMocks(this)
        initRxSchedulers()
        testObserver = TestObserver.create()
        adsServiceManager = AdsServiceManager(adsService)

    }

    @Test
    fun get() {
        val storyList = fakeAdList(10)
        whenever(adsService.get()).thenReturn(Observable.just(storyList))
        adsServiceManager.get().subscribe(testObserver)
        testObserver.assertValue { returnedStoryList ->
            assertEquals(storyList, returnedStoryList)
            true
        }
    }
}