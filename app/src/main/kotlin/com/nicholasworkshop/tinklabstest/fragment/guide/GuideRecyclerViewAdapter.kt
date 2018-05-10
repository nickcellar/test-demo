package com.nicholasworkshop.tinklabstest.fragment.guide

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.nicholasworkshop.tinklabstest.R
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad
import com.nicholasworkshop.tinklabstest.external.content.model.Story
import com.nicholasworkshop.tinklabstest.widget.AdItemView
import com.nicholasworkshop.tinklabstest.widget.RecyclerViewHolder
import com.nicholasworkshop.tinklabstest.widget.StoryItemView
import java.util.Random

import javax.inject.Inject

internal const val TYPE_STORY = 1
internal const val TYPE_AD = 0

class GuideRecyclerViewAdapter @Inject internal constructor(
        private val layoutInflater: LayoutInflater)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adOffset = 1
    private var adInterval = 5
    private var adList: List<Ad>? = null
    private var storyList: List<Story>? = null

    /**
     * Only 2 types: ad or story
     */
    override fun getItemViewType(position: Int): Int {
        return if ((position - adOffset) % adInterval == 0) TYPE_AD else TYPE_STORY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_AD) {
            return RecyclerViewHolder(AdItemView(parent.context))

        } else if (viewType == TYPE_STORY) {
            return RecyclerViewHolder(StoryItemView(parent.context))
        }
        throw RuntimeException()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == TYPE_AD) {
            val view = holder.itemView as AdItemView
            if (adList != null && adList!!.isNotEmpty()) {
                val index = Random().nextInt(adList!!.size)
                val ad = adList!![index]
                view.setImageUrl(ad.imageUrl)
                view.redirectUrl = ad.redirectUrl
            } else {
                view.clear()
            }
        } else if (viewType == TYPE_STORY) {
            val story = storyList!![getStoryListIndex(position)]
            val view = holder.itemView as StoryItemView
            view.setTitle(story.title)
            view.setSummary(story.summary)
            view.setFeatureImageUrl(story.featureImageUrl)
        } else {
            throw RuntimeException()
        }
    }

    /**
     * Total number of ads and stories
     */
    override fun getItemCount(): Int {
        val storyCount = if (storyList != null) storyList!!.size else 0
        val adCount = storyCount / adInterval
        return storyCount + adCount
    }

    internal fun setStoryList(storyList: List<Story>) {
        this.storyList = storyList
        notifyDataSetChanged()
    }

    internal fun setAdList(adList: List<Ad>) {
        this.adList = adList
        notifyDataSetChanged()
    }

    /**
     * Offset for an ads to start with.
     * i.e. Setting this to 5, ads will start
     * only after the 5th item
     */
    internal fun setAdOffset(adOffset: Int) {
        this.adOffset = adOffset
    }

    /**
     * Position interval for ads to show.
     * i.e. By setting this to 5, ads will be shown
     * every 5th position, after the offset in
     * the beginning
     */
    internal fun setAdInterval(adInterval: Int) {
        this.adInterval = adInterval
    }

    /**
     * Translate the list position to the
     * actual position in the story list.
     */
    internal fun getStoryListIndex(position: Int): Int {
        return if (position < adOffset) {
            position
        } else {
            position - (position - adOffset) / adInterval - 1
        }
    }
}