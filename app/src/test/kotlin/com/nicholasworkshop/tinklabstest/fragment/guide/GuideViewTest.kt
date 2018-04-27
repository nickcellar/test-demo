package com.nicholasworkshop.tinklabstest.fragment.guide

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nhaarman.mockito_kotlin.whenever
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad
import com.nicholasworkshop.tinklabstest.external.content.model.Story
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

class GuideViewTest {

    @Mock private lateinit var guideRecyclerViewAdapter: GuideRecyclerViewAdapter
    @Mock private lateinit var recyclerView: RecyclerView
    @Mock private lateinit var storyList: List<Story>
    @Mock private lateinit var adList: List<Ad>
    @Mock private lateinit var linearLayoutManager: LinearLayoutManager

    @Captor private lateinit var guideInfiniteScrollListenerArgumentCaptor: ArgumentCaptor<GuideView.GuideInfiniteScrollListener>

    private val testObserver = TestObserver.create<Int>()

    private lateinit var guideView: GuideView

    @Before
    fun setUp() {
        initMocks(this)
        guideView = GuideView(guideRecyclerViewAdapter)
        guideView.storiesView = recyclerView
        guideView.layoutManager = linearLayoutManager
    }

    @Test
    fun viewCreated() {
        guideView.viewCreated()
        verify(recyclerView).setHasFixedSize(true)
        verify(recyclerView).layoutManager = any(RecyclerView.LayoutManager::class.java)
        verify(recyclerView).adapter = guideRecyclerViewAdapter
        verify(recyclerView).addOnScrollListener(any(RecyclerView.OnScrollListener::class.java))
    }

    @Test
    fun setStoryList() {
        guideView.setStoryList(storyList)
        verify(guideRecyclerViewAdapter).setStoryList(storyList)
    }

    @Test
    fun setAdList() {
        guideView.setAdList(adList)
        verify(guideRecyclerViewAdapter).setAdList(adList)
    }

    @Test
    fun storyRequests() {
        guideView.viewCreated()
        guideView.storyRequests().subscribe(testObserver)
        whenever(linearLayoutManager.findLastVisibleItemPosition()).thenReturn(1234)
        verify(recyclerView).addOnScrollListener(guideInfiniteScrollListenerArgumentCaptor.capture())
        guideInfiniteScrollListenerArgumentCaptor.value.onScrolledToEnd(9999)
        testObserver.assertValue(1234)
    }
}