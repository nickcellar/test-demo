package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.nicholasworkshop.tinklabstest.R;
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
public class GuideView {

    private final GuideRecyclerViewAdapter guideRecyclerViewAdapter;

    @BindView(R.id.stories) RecyclerView storiesRecyclerView;

    @Inject
    GuideView(GuideRecyclerViewAdapter guideRecyclerViewAdapter) {
        this.guideRecyclerViewAdapter = guideRecyclerViewAdapter;
    }

    View createView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        storiesRecyclerView.setHasFixedSize(true);
        storiesRecyclerView.setLayoutManager(layoutManager);
        storiesRecyclerView.setAdapter(guideRecyclerViewAdapter);
        storiesRecyclerView.addOnScrollListener(new GuideInfiniteScrollListener(1, layoutManager));
        return view;
    }

    void setStoryList(List<Story> storyList) {
        guideRecyclerViewAdapter.setStoryList(storyList);
    }

    void setAdList(List<Ad> adList) {
        guideRecyclerViewAdapter.setAdList(adList);
    }

    private Subject<Integer> storyRequestSubject = BehaviorSubject.create();

    Observable<Integer> storyRequests() {
        return storyRequestSubject;
    }

    private class GuideInfiniteScrollListener extends InfiniteScrollListener {

        GuideInfiniteScrollListener(int maxItemsPerRequest, LinearLayoutManager layoutManager) {
            super(maxItemsPerRequest, layoutManager);
        }

        @Override
        public void onScrolledToEnd(int firstVisibleItemPosition) {
            Log.d("dsafsdaf", "fdsafsadf" + firstVisibleItemPosition);
            storyRequestSubject.onNext(firstVisibleItemPosition);
        }
    }
}
