package com.nicholasworkshop.tinklabstest.fragment.guide;

import com.nicholasworkshop.tinklabstest.external.ads.AdsServiceManager;
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;
import com.nicholasworkshop.tinklabstest.external.content.ContentServiceManager;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

public class GuideModel {

    private final AdsServiceManager adsServiceManager;
    private final ContentServiceManager contentServiceManager;

    @Inject
    GuideModel(AdsServiceManager adsServiceManager, ContentServiceManager contentServiceManager) {
        this.adsServiceManager = adsServiceManager;
        this.contentServiceManager = contentServiceManager;
    }

    Observable<List<Story>> storyList(int count) {
        return contentServiceManager.getGuide(count);
    }

    Observable<List<Ad>> ads() {
        return adsServiceManager.get();
    }
}
