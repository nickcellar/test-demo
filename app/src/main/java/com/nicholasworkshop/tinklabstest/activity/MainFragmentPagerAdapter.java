package com.nicholasworkshop.tinklabstest.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nicholasworkshop.tinklabstest.fragment.guide.GuideFragment;
import com.nicholasworkshop.tinklabstest.fragment.guide.GuideModule;

class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    MainFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GuideFragment.newInstance(GuideModule.TYPE_CITY);
            case 1:
                return GuideFragment.newInstance(GuideModule.TYPE_SHOP);
            case 2:
                return GuideFragment.newInstance(GuideModule.TYPE_EAT);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CITY GUIDE";
            case 1:
                return "SHOP";
            case 2:
                return "EAT";
        }
        return null;
    }
}
