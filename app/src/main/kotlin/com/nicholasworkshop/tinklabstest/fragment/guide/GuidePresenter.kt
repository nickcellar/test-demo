package com.nicholasworkshop.tinklabstest.fragment.guide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import javax.inject.Inject


private const val INITIAL_STORY_COUNT = 4
private const val FETCH_STORY_COUNT = 10

internal class GuidePresenter @Inject constructor(
        private val context: Context,
        private val guideView: GuideView,
        private val guideModel: GuideModel) {

    private var storyFetching = true

    fun createView(inflater: LayoutInflater, container: ViewGroup): View {
        return guideView.createView(inflater, container)
    }

    fun viewCreated(@GuideType guideType: Int) {
        guideView.viewCreated()

        // fetch stories when the app first starts
        guideModel
                .storyList(guideType, INITIAL_STORY_COUNT)!!
                .doOnNext { storyFetching = false }
                .subscribe({ guideView.setStoryList(it) }, { this.handleException(it) })

        // fetch stories when view is scrolled to the bottom, and request more data
        // and it fetch only when no other threads are fetching
        guideView
                .storyRequests()
                .filter { !storyFetching }
                .doOnNext { storyFetching = true }
                .flatMap { guideModel.storyList(guideType, it + FETCH_STORY_COUNT)!! }
                .doOnNext { storyFetching = false }
                .subscribe({ guideView.setStoryList(it) }, { this.handleException(it) })

        // fetch ads and send to the view
        guideModel
                .ads()
                .subscribe({ guideView.setAdList(it) }, { this.handleException(it) })
    }

    private fun handleException(error: Throwable) {
        Toast.makeText(context, "Failed to fetch data\n${error.localizedMessage}", Toast.LENGTH_LONG).show()
    }
}