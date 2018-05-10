package com.nicholasworkshop.tinklabstest.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.nicholasworkshop.tinklabstest.MainApplication
import com.nicholasworkshop.tinklabstest.R
import kotlinx.android.synthetic.main.view_item_story.view.*
import javax.inject.Inject

class StoryItemView : LinearLayout {

    @Inject lateinit var requestManager: RequestManager
    @Inject lateinit var layoutInflater: LayoutInflater

    internal var titleView: TextView
    internal var summaryView: TextView
    internal var featureView: ImageView

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        (context.applicationContext as MainApplication).component.inject(this)
        layoutInflater.inflate(R.layout.view_item_story, this, true)
        titleView = title_view
        summaryView = summary_view
        featureView = feature_view
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    fun setTitle(title: String?) {
        titleView.text = title
    }

    fun setSummary(summary: String?) {
        summaryView.text = summary
    }

    fun setFeatureImageUrl(featureImageUrl: String?) {
        requestManager.load(featureImageUrl).into(featureView)
    }
}
