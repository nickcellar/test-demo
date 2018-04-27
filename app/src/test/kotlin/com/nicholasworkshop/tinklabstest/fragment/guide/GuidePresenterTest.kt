package com.nicholasworkshop.tinklabstest.fragment.guide

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

class GuidePresenterTest {

    @Mock private lateinit var context: Context
    @Mock private lateinit var guideView: GuideView
    @Mock private lateinit var guideModel: GuideModel
    @Mock private lateinit var layoutInflater: LayoutInflater
    @Mock private lateinit var viewGroup: ViewGroup
    @Mock private lateinit var guideType: GuideType

    private lateinit var guidePresenter: GuidePresenter

    @Before
    fun setUp() {
        initMocks(this)
        whenever(guideModel.storyList(any(), anyInt())).thenReturn(Observable.empty())
        whenever(guideModel.ads()).thenReturn(Observable.empty())
        whenever(guideView.storyRequests()).thenReturn(Observable.empty())
        guidePresenter = GuidePresenter(context, guideView, guideModel)
    }

    @Test
    fun createView() {
        guidePresenter.createView(layoutInflater, viewGroup)
        verify(guideView).createView(layoutInflater, viewGroup)
    }

    @Test
    fun viewCreated() {
        guidePresenter.viewCreated(guideType)
        verify(guideView).viewCreated()
        verify(guideModel).storyList(eq(guideType), anyInt())
        verify(guideView).storyRequests()
        verify(guideModel).ads()
    }
}