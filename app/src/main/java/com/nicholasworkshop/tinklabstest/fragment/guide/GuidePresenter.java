package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

class GuidePresenter {

    private static final int INITIAL_STORY_COUNT = 4;
    private static final int FETCH_STORY_COUNT = 10;

    private final GuideView guideView;
    private final GuideModel guideModel;
    private boolean storyFetching = true;

    @Inject
    GuidePresenter(GuideView guideView, GuideModel guideModel) {
        this.guideView = guideView;
        this.guideModel = guideModel;
    }

    View createView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return guideView.createView(inflater, container);
    }

    void viewCreated(View view, @Nullable Bundle savedInstanceState) {
        guideModel
                .storyList(INITIAL_STORY_COUNT)
                .doOnNext(stories -> storyFetching = false)
                .subscribe(guideView::setStoryList);
        guideView
                .storyRequests()
                .filter(integer -> !storyFetching)
                .doOnNext(stories -> storyFetching = true)
                .flatMap(firstVisibleItemPosition -> guideModel.storyList(firstVisibleItemPosition + FETCH_STORY_COUNT))
                .doOnNext(stories -> storyFetching = false)
                .subscribe(guideView::setStoryList);
        guideModel
                .ads()
                .subscribe(guideView::setAdList);
    }
}
