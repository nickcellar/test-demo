package com.nicholasworkshop.tinklabstest.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.nicholasworkshop.tinklabstest.MainApplication
import kotlinx.android.synthetic.main.view_item_story.view.*
import javax.inject.Inject

class StoryItemView : LinearLayout {

    @Inject lateinit var requestManager: RequestManager

    var titleView: TextView? = title_view
    var summaryView: TextView? = summary_view
    var featureView: ImageView? = feature_view

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        (context.applicationContext as MainApplication).component.inject(this)
    }

    fun setTitle(title: String?) {
        titleView!!.text = title
    }

    fun setSummary(summary: String?) {
        summaryView!!.text = summary
    }

    fun setFeatureImageUrl(featureImageUrl: String?) {
        requestManager.load(featureImageUrl).into(featureView!!)
    }
}
