package com.nicholasworkshop.tinklabstest.widget

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.github.javafaker.Faker
import com.nhaarman.mockito_kotlin.whenever
import com.nicholasworkshop.tinklabstest.MainApplication
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.Spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, application = MainApplication::class)
class AdItemViewTest {

    private val faker = Faker()

    @Spy private var context: Context = RuntimeEnvironment.application

    @Mock private lateinit var adView: ImageView
    @Mock private lateinit var requestManager: RequestManager
    @Mock private lateinit var requestBuilder: RequestBuilder<Drawable>

    @Captor private lateinit var intentCaptor: ArgumentCaptor<Intent>

    private lateinit var adItemView: AdItemView

    @Before
    fun setUp() {
        initMocks(this)
        whenever(requestManager.load(anyString())).thenReturn(requestBuilder)
        adItemView = AdItemView(context)
        adItemView.requestManager = requestManager
        adItemView.adView = adView
    }

    @Test
    fun setImageUrl() {
        val url = faker.internet().image()
        adItemView.setImageUrl(url)
        verify(requestManager).load(url)
        verify(requestBuilder).into(adItemView.adView)
    }

    @Test
    fun clear() {
        adItemView.setImageUrl(faker.internet().image())
        adItemView.redirectUrl = faker.internet().url()
        adItemView.clear()
        assertNull(adItemView.adView.drawable)
        verify(adView).setImageDrawable(null)
    }

    @Test
    fun onClicked() {
        val url = faker.internet().url()
        adItemView.redirectUrl = url
        adItemView.performClick()
        verify(context).startActivity(intentCaptor.capture())
        val intent = intentCaptor.value
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertEquals(Intent.FLAG_ACTIVITY_NEW_TASK.toLong(), intent.flags.toLong())
        assertEquals(url, intent.data.toString())
    }

    @Test
    fun onClicked_whenRedirectUrlNotSet_thenNothingHappens() {
        adItemView.performClick()
        verify(context, never()).startActivity(any())
    }

    @Test
    fun onClicked_whenCleared_thenNothingHappens() {
        adItemView.setImageUrl(faker.internet().image())
        adItemView.redirectUrl = faker.internet().url()
        adItemView.clear()
        adItemView.performClick()
        verify(context, never()).startActivity(any())
    }
}