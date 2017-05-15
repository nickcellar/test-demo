package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicholasworkshop.tinklabstest.R;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, view);
        storiesRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        storiesRecyclerView.setAdapter(guideRecyclerViewAdapter);
        return view;
    }

    void setStoryList(List<Story> storyList) {
        guideRecyclerViewAdapter.setStoryList(storyList);
    }
}
