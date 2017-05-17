package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by nicholas.wong on 2017/05/17.
 */
public interface GuideModule {

    @Retention(SOURCE)
    @IntDef({TYPE_CITY, TYPE_SHOP, TYPE_EAT})
    @interface GuideType {
    }

    int TYPE_CITY = 0;
    int TYPE_SHOP = 1;
    int TYPE_EAT = 2;
}
