package com.nicholasworkshop.tinklabstest.external.content;

import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Singleton
public class ContentServiceManager {

    private final ContentService contentService;

    @Inject
    ContentServiceManager(ContentService contentService) {
        this.contentService = contentService;
    }

    public Observable<List<Story>> getGuide(int count) {
        return contentService
                .getGuide()
                .cache()
                .map(stories -> stories.subList(0, Math.min(stories.size(), count)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
