package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

class GuidePresenter {

    private static final int INITIAL_STORY_COUNT = 4;
    private static final int FETCH_STORY_COUNT = 10;

    private final Context context;
    private final GuideView guideView;
    private final GuideModel guideModel;
    private boolean storyFetching = true;

    @Inject
    GuidePresenter(Context context, GuideView guideView, GuideModel guideModel) {
        this.context = context;
        this.guideView = guideView;
        this.guideModel = guideModel;
    }

    void setGuideType(@GuideModule.GuideType int guideType) {
        guideModel.setGuideType(guideType);
    }

    View createView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return guideView.createView(inflater, container);
    }

    void viewCreated(View view, @Nullable Bundle savedInstanceState) {
        guideView.viewCreated(view);
        guideModel
                .storyList(INITIAL_STORY_COUNT)
                .doOnNext(stories -> storyFetching = false)
                .subscribe(guideView::setStoryList, this::handleException);
        guideView
                .storyRequests()
                .filter(integer -> !storyFetching)
                .doOnNext(stories -> storyFetching = true)
                .flatMap(lastPosition -> guideModel.storyList(lastPosition + FETCH_STORY_COUNT))
                .doOnNext(stories -> storyFetching = false)
                .subscribe(guideView::setStoryList, this::handleException);
        guideModel
                .ads()
                .subscribe(guideView::setAdList, this::handleException);
    }

    private void handleException(Throwable error) {
        Toast.makeText(context, "Failed to fetch data\n" + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }
}
