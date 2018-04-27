package com.nicholasworkshop.tinklabstest.fragment.guide

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener
import com.nicholasworkshop.tinklabstest.R
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad
import com.nicholasworkshop.tinklabstest.external.content.model.Story
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_guide.*
import javax.inject.Inject

class GuideView @Inject internal constructor(
        private val guideRecyclerViewAdapter: GuideRecyclerViewAdapter)
    : LayoutContainer {

    override lateinit var containerView: View

    lateinit var storiesView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager

    private val storyRequestSubject = BehaviorSubject.create<Int>()

    internal fun createView(inflater: LayoutInflater, container: ViewGroup): View {
        containerView = inflater.inflate(R.layout.fragment_guide, container, false)
        layoutManager = LinearLayoutManager(containerView.context)
        storiesView = stories_view
        return containerView
    }

    internal fun viewCreated() {
        storiesView.setHasFixedSize(true)
        storiesView.layoutManager = layoutManager
        storiesView.adapter = guideRecyclerViewAdapter
        storiesView.addOnScrollListener(GuideInfiniteScrollListener(1, layoutManager))
    }

    internal fun setStoryList(storyList: List<Story>) {
        guideRecyclerViewAdapter.setStoryList(storyList)
    }

    internal fun setAdList(adList: List<Ad>) {
        guideRecyclerViewAdapter.setAdList(adList)
    }

    internal fun storyRequests(): Observable<Int> {
        return storyRequestSubject
    }

    internal inner class GuideInfiniteScrollListener(maxItemsPerRequest: Int, layoutManager: LinearLayoutManager)
        : InfiniteScrollListener(maxItemsPerRequest, layoutManager) {

        override fun onScrolledToEnd(firstVisibleItemPosition: Int) {
            storyRequestSubject.onNext(layoutManager.findLastVisibleItemPosition())
        }
    }
}
