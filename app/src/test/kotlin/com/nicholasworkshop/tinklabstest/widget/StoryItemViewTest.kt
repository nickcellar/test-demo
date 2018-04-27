package com.nicholasworkshop.tinklabstest.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.github.javafaker.Faker
import com.nhaarman.mockito_kotlin.whenever
import com.nicholasworkshop.tinklabstest.MainApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.Spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, application = MainApplication::class)
class StoryItemViewTest {

    private val faker = Faker()

    @Spy private var context: Context = RuntimeEnvironment.application

    @Mock private lateinit var titleView: TextView
    @Mock private lateinit var summaryView: TextView
    @Mock private lateinit var featureView: ImageView
    @Mock private lateinit var requestManager: RequestManager
    @Mock private lateinit var requestBuilder: RequestBuilder<Drawable>

    private lateinit var storyItemView: StoryItemView

    @Before
    fun setUp() {
        initMocks(this)
        whenever(requestManager.load(anyString())).thenReturn(requestBuilder)
        storyItemView = StoryItemView(context)
        storyItemView.titleView = titleView
        storyItemView.summaryView = summaryView
        storyItemView.featureView = featureView
        storyItemView.requestManager = requestManager
    }

    @Test
    fun setTitle() {
        val text = faker.shakespeare().asYouLikeItQuote()
        storyItemView.setTitle(text)
        verify(titleView).text = text
    }

    @Test
    fun setSummary() {
        val text = faker.shakespeare().asYouLikeItQuote()
        storyItemView.setSummary(text)
        verify(summaryView).text = text
    }

    @Test
    fun setFeatureImageUrl() {
        val text = faker.internet().image()
        storyItemView.setFeatureImageUrl(text)
        verify(requestManager).load(text)
        verify(requestBuilder).into(featureView)
    }
}