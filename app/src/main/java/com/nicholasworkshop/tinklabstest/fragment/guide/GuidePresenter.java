package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by nicholas.wong on 2017/05/15.
 *
 * This is the main presenter that control data flow between fragment,
 * model, view and other necessary processing
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

    View createView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return guideView.createView(inflater, container);
    }

    void viewCreated(View view, @GuideModule.GuideType int guideType) {
        guideView.viewCreated(view);

        // fetch stories when the app first starts
        guideModel
                .storyList(guideType, INITIAL_STORY_COUNT)
                .doOnNext(stories -> storyFetching = false)
                .subscribe(guideView::setStoryList, this::handleException);

        // fetch stories when view is scrolled to the bottom, and request more data
        // and it fetch only when no other threads are fetching
        guideView
                .storyRequests()
                .filter(integer -> !storyFetching)
                .doOnNext(stories -> storyFetching = true)
                .flatMap(lastPosition -> guideModel.storyList(guideType, lastPosition + FETCH_STORY_COUNT))
                .doOnNext(stories -> storyFetching = false)
                .subscribe(guideView::setStoryList, this::handleException);

        // fetch ads and send to the view
        guideModel
                .ads()
                .subscribe(guideView::setAdList, this::handleException);
    }

    private void handleException(Throwable error) {
        Toast.makeText(context, "Failed to fetch data\n" + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }
}
