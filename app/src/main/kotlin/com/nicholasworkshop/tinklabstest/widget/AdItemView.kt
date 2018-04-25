package com.nicholasworkshop.tinklabstest.widget

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.widget.FrameLayout
import com.bumptech.glide.RequestManager
import com.nicholasworkshop.tinklabstest.MainApplication
import kotlinx.android.synthetic.main.view_item_ad.view.*
import kotlinx.android.synthetic.main.view_item_story.view.*
import javax.inject.Inject

class AdItemView : FrameLayout {

    @Inject
    lateinit var requestManager: RequestManager

    var redirectUrl: String? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        (context.applicationContext as MainApplication).component.inject(this)
        setOnClickListener {
            if (redirectUrl != null) {
                val uri = Uri.parse(redirectUrl)
                val intent = Intent()
                intent.data = uri
                intent.action = Intent.ACTION_VIEW
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
    }

    fun setImageUrl(imageUrl: String?) {
        requestManager.load(imageUrl).into(adView)
    }

    fun clear() {
        adView.setImageDrawable(null)
        redirectUrl = null
    }
}
