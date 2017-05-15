package com.nicholasworkshop.tinklabstest.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.nicholasworkshop.tinklabstest.MainApplication;
import com.nicholasworkshop.tinklabstest.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
public class AdItemView extends FrameLayout {

    @BindView(R.id.ad) ImageView adImageView;

    @Inject RequestManager requestManager;

    private String redirectUrl;

    public AdItemView(Context context) {
        this(context, null);
    }

    public AdItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ((MainApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setImageUrl(String imageUrl) {
        requestManager.load(imageUrl).into(adImageView);
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void clear() {
        adImageView.setImageDrawable(null);
        redirectUrl = null;
    }

    @OnClick
    public void onClicked() {
        if (redirectUrl != null) {
            Uri uri = Uri.parse(redirectUrl);
            Intent intent = new Intent();
            intent.setData(uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }
}
