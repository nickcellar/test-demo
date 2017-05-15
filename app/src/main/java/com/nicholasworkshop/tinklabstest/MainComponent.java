package com.nicholasworkshop.tinklabstest;

import com.nicholasworkshop.tinklabstest.activity.MainActivity;
import com.nicholasworkshop.tinklabstest.external.ads.AdsService;
import com.nicholasworkshop.tinklabstest.external.ads.AdsServiceModule;
import com.nicholasworkshop.tinklabstest.external.content.ContentServiceModule;
import com.nicholasworkshop.tinklabstest.fragment.PlaceholderFragment;
import com.nicholasworkshop.tinklabstest.fragment.guide.GuideFragment;
import com.nicholasworkshop.tinklabstest.widget.StoryItemView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Singleton
@Component(modules = {
        MainModule.class,
        AdsServiceModule.class,
        ContentServiceModule.class,
})
public interface MainComponent {

    void inject(MainActivity mainActivity);

    void inject(PlaceholderFragment placeholderFragment);

    void inject(GuideFragment guideFragment);

    void inject(StoryItemView storyItemView);
}
