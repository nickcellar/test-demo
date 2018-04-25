package com.nicholasworkshop.tinklabstest.activity

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nicholasworkshop.tinklabstest.R
import com.nicholasworkshop.tinklabstest.fragment.guide.TYPE_CITY
import com.nicholasworkshop.tinklabstest.fragment.guide.TYPE_EAT
import com.nicholasworkshop.tinklabstest.fragment.guide.TYPE_SHOP
import com.nicholasworkshop.tinklabstest.fragment.guide.newGuideFragmentInstance

internal class MainFragmentPagerAdapter(
        private val context: Context,
        fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return newGuideFragmentInstance(TYPE_CITY)
            1 -> return newGuideFragmentInstance(TYPE_SHOP)
            2 -> return newGuideFragmentInstance(TYPE_EAT)
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context.getString(R.string.main_tab_city)
            1 -> return context.getString(R.string.main_tab_shop)
            2 -> return context.getString(R.string.main_tab_eat)
        }
        return null
    }
}
