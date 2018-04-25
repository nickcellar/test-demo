package com.nicholasworkshop.tinklabstest.fragment.guide

import android.support.annotation.IntDef

class GuideModule

@Retention(AnnotationRetention.SOURCE)
@IntDef(TYPE_CITY, TYPE_SHOP, TYPE_EAT)
annotation class GuideType

const val TYPE_CITY = 0
const val TYPE_SHOP = 1
const val TYPE_EAT = 2