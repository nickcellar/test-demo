package com.nicholasworkshop.tinklabstest.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.nicholasworkshop.tinklabstest.MainApplication;
import com.nicholasworkshop.tinklabstest.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
public class StoryItemView extends LinearLayout {

    @BindView(R.id.title) TextView titleTextView;
    @BindView(R.id.summary) TextView summaryTextView;
    @BindView(R.id.feature) ImageView featureTextView;

    @Inject RequestManager requestManager;

    public StoryItemView(Context context) {
        this(context, null);
    }

    public StoryItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StoryItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ((MainApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setTitle(String title) {
        this.titleTextView.setText(title);
    }

    public void setSummary(String summary) {
        this.summaryTextView.setText(summary);
    }

    public void setFeatureImageUrl(String featureImageUrl) {
        requestManager.load(featureImageUrl).into(featureTextView);
    }
}
