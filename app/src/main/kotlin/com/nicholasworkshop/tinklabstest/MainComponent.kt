package com.nicholasworkshop.tinklabstest

import com.nicholasworkshop.tinklabstest.activity.MainActivity
import com.nicholasworkshop.tinklabstest.external.ads.AdsServiceModule
import com.nicholasworkshop.tinklabstest.external.content.ContentServiceModule
import com.nicholasworkshop.tinklabstest.fragment.guide.GuideFragment
import com.nicholasworkshop.tinklabstest.widget.AdItemView
import com.nicholasworkshop.tinklabstest.widget.StoryItemView

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [
    MainModule::class,
    AdsServiceModule::class,
    ContentServiceModule::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(guideFragment: GuideFragment)

    fun inject(storyItemView: StoryItemView)

    fun inject(adItemView: AdItemView)
}
