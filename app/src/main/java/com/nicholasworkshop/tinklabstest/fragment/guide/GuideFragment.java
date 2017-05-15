package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicholasworkshop.tinklabstest.MainApplication;
import com.nicholasworkshop.tinklabstest.MainComponent;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

public class GuideFragment extends Fragment {

    @Inject GuideView guideView;
    @Inject GuideModel guideModel;

    public static GuideFragment newInstance() {
        return new GuideFragment();
    }

    public GuideFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponent component = ((MainApplication) this.getActivity().getApplication()).getComponent();
        component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return guideView.createView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        guideModel.storyList().subscribe(new Consumer<List<Story>>() {
            @Override
            public void accept(@NonNull List<Story> stories) throws Exception {
                guideView.setStoryList(stories);
            }
        });
    }
}
