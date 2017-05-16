package com.nicholasworkshop.tinklabstest.fragment.guide;

import com.nicholasworkshop.tinklabstest.external.ads.AdsService;
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;
import com.nicholasworkshop.tinklabstest.external.content.ContentService;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

public class GuideModel {

    private final AdsService adsService;
    private final ContentService contentService;

    @Inject
    GuideModel(AdsService adsService, ContentService contentService) {
        this.adsService = adsService;
        this.contentService = contentService;
    }

    Observable<List<Story>> storyList(final int count) {
        return contentService
                .getGuide()
                .cache()
                .map(stories -> stories.subList(0, Math.min(stories.size(), count)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<List<Ad>> ads() {
        return adsService
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
