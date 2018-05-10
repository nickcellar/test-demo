package com.nicholasworkshop.tinklabstest.fragment.guide

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.nhaarman.mockito_kotlin.whenever
import com.nicholasworkshop.tinklabstest.MainApplication
import com.nicholasworkshop.tinklabstest.R
import com.nicholasworkshop.tinklabstest.external.ads.model.AdFaker.fakeAdList
import com.nicholasworkshop.tinklabstest.external.content.model.StoryFaker.fakeStoryList
import com.nicholasworkshop.tinklabstest.widget.AdItemView
import com.nicholasworkshop.tinklabstest.widget.RecyclerViewHolder
import com.nicholasworkshop.tinklabstest.widget.StoryItemView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.Spy
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, application = MainApplication::class)
class GuideRecyclerViewAdapterTest {

    @Spy private val viewGroup = FrameLayout(RuntimeEnvironment.application)

    @Mock private lateinit var adItemView: AdItemView
    @Mock private lateinit var storyItemView: StoryItemView
    @Mock private lateinit var layoutInflater: LayoutInflater
    @Mock private lateinit var observer: RecyclerView.AdapterDataObserver

    private lateinit var guideRecyclerViewAdapter: GuideRecyclerViewAdapter

    @Before
    fun setUp() {
        initMocks(this)
        guideRecyclerViewAdapter = GuideRecyclerViewAdapter(layoutInflater)
    }

    @Test
    fun getItemViewType() {
        guideRecyclerViewAdapter.setAdOffset(4)
        guideRecyclerViewAdapter.setAdInterval(8)
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(0))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(1))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(2))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(3))
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(4))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(5))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(6))
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(12))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(13))
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(20))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(21))
    }

    @Test
    fun getItemViewType2() {
        guideRecyclerViewAdapter.setAdOffset(1)
        guideRecyclerViewAdapter.setAdInterval(5)
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(0))
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(1))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(2))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(3))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(4))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(5))
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(6))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(7))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(8))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(9))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(10))
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(11))
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(20))
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(21))
    }

    @Test
    fun onCreateViewHolder_whenCreatingAd() {
        val holder = guideRecyclerViewAdapter.onCreateViewHolder(viewGroup, TYPE_AD)
        assertNotNull(holder)
        assertNotNull(holder.itemView)
    }

    @Test
    fun onCreateViewHolder_whenCreatingStory() {
        val holder = guideRecyclerViewAdapter.onCreateViewHolder(viewGroup, TYPE_STORY)
        assertNotNull(holder)
        assertNotNull(holder.itemView)
    }

    @Test(expected = RuntimeException::class)
    fun onCreateViewHolder_throwExceptionIfTypeIsUnknown() {
        guideRecyclerViewAdapter.onCreateViewHolder(viewGroup, 13254)
    }

    @Test
    fun onBindViewHolder_whenBindingStoryView() {
        val storyList = fakeStoryList(10)
        val holder = RecyclerViewHolder(storyItemView)
        guideRecyclerViewAdapter.setAdOffset(4)
        guideRecyclerViewAdapter.setAdInterval(8)
        guideRecyclerViewAdapter.setStoryList(storyList)
        guideRecyclerViewAdapter.onBindViewHolder(holder, 3)
        val (title, summary, featureImageUrl) = storyList[3]
        verify(storyItemView).setTitle(title)
        verify(storyItemView).setSummary(summary)
        verify(storyItemView).setFeatureImageUrl(featureImageUrl)
    }

    @Test
    fun onBindViewHolder_whenBindingAdView() {
        val adList = fakeAdList(1)
        val holder = RecyclerViewHolder(adItemView)
        guideRecyclerViewAdapter.setAdOffset(4)
        guideRecyclerViewAdapter.setAdInterval(8)
        guideRecyclerViewAdapter.setAdList(adList)
        guideRecyclerViewAdapter.onBindViewHolder(holder, 4)
        val (imageUrl, redirectUrl) = adList[0]
        verify<AdItemView>(adItemView).setImageUrl(imageUrl)
        verify<AdItemView>(adItemView).redirectUrl = redirectUrl
    }

    @Test
    fun onBindViewHolder_whenBindingAdView_clearWithNoAdsAvailable() {
        val holder = RecyclerViewHolder(adItemView)
        guideRecyclerViewAdapter.setAdOffset(4)
        guideRecyclerViewAdapter.setAdInterval(8)
        guideRecyclerViewAdapter.onBindViewHolder(holder, 4)
        verify<AdItemView>(adItemView).clear()
    }

    @Test
    fun getItemCount() {
        guideRecyclerViewAdapter.setAdOffset(4)
        guideRecyclerViewAdapter.setAdInterval(8)
        guideRecyclerViewAdapter.setStoryList(fakeStoryList(10))
        assertEquals(11, guideRecyclerViewAdapter.itemCount.toLong())
    }

    @Test
    fun setStoryList() {
        guideRecyclerViewAdapter.registerAdapterDataObserver(observer)
        guideRecyclerViewAdapter.setStoryList(fakeStoryList(10))
        verify(observer).onChanged()
    }

    @Test
    fun setAdList() {
        guideRecyclerViewAdapter.registerAdapterDataObserver(observer)
        guideRecyclerViewAdapter.setAdList(fakeAdList(10))
        verify(observer).onChanged()
    }

    @Test
    fun getStoryListIndex() {
        guideRecyclerViewAdapter.setAdOffset(4)
        guideRecyclerViewAdapter.setAdInterval(8)
        assertEquals(0, guideRecyclerViewAdapter.getStoryListIndex(0))
        assertEquals(1, guideRecyclerViewAdapter.getStoryListIndex(1))
        assertEquals(2, guideRecyclerViewAdapter.getStoryListIndex(2))
        assertEquals(3, guideRecyclerViewAdapter.getStoryListIndex(3))
        assertEquals(4, guideRecyclerViewAdapter.getStoryListIndex(5))
        assertEquals(5, guideRecyclerViewAdapter.getStoryListIndex(6))
        assertEquals(6, guideRecyclerViewAdapter.getStoryListIndex(7))
        assertEquals(7, guideRecyclerViewAdapter.getStoryListIndex(8))
        assertEquals(8, guideRecyclerViewAdapter.getStoryListIndex(9))
        assertEquals(9, guideRecyclerViewAdapter.getStoryListIndex(10))
        assertEquals(10, guideRecyclerViewAdapter.getStoryListIndex(11))
        assertEquals(11, guideRecyclerViewAdapter.getStoryListIndex(13))
        assertEquals(18, guideRecyclerViewAdapter.getStoryListIndex(21))
    }

    @Test
    fun getStoryListIndex2() {
        guideRecyclerViewAdapter.setAdOffset(1)
        guideRecyclerViewAdapter.setAdInterval(5)
        assertEquals(0, guideRecyclerViewAdapter.getStoryListIndex(0))
        assertEquals(1, guideRecyclerViewAdapter.getStoryListIndex(2))
        assertEquals(2, guideRecyclerViewAdapter.getStoryListIndex(3))
        assertEquals(3, guideRecyclerViewAdapter.getStoryListIndex(4))
        assertEquals(4, guideRecyclerViewAdapter.getStoryListIndex(5))
        assertEquals(5, guideRecyclerViewAdapter.getStoryListIndex(7))
        assertEquals(6, guideRecyclerViewAdapter.getStoryListIndex(8))
        assertEquals(7, guideRecyclerViewAdapter.getStoryListIndex(9))
        assertEquals(8, guideRecyclerViewAdapter.getStoryListIndex(10))
        assertEquals(9, guideRecyclerViewAdapter.getStoryListIndex(12))
        assertEquals(10, guideRecyclerViewAdapter.getStoryListIndex(13))
        assertEquals(11, guideRecyclerViewAdapter.getStoryListIndex(14))
        assertEquals(12, guideRecyclerViewAdapter.getStoryListIndex(15))
        assertEquals(13, guideRecyclerViewAdapter.getStoryListIndex(17))
        assertEquals(16, guideRecyclerViewAdapter.getStoryListIndex(21))
    }
}