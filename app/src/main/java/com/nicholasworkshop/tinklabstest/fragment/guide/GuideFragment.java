package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicholasworkshop.tinklabstest.MainApplication;
import com.nicholasworkshop.tinklabstest.MainComponent;

import javax.inject.Inject;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

public class GuideFragment extends Fragment {

    private static final String ARG_GUIDE_TYPE = "ARG_GUIDE_TYPE";

    @Inject GuidePresenter guidePresenter;

    public static GuideFragment newInstance(@GuideModule.GuideType int guideType) {
        GuideFragment fragment = new GuideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_GUIDE_TYPE, guideType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainComponent component = ((MainApplication) this.getActivity().getApplication()).getComponent();
        component.inject(this);
        guidePresenter.setGuideType(getArguments().getInt(ARG_GUIDE_TYPE));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return guidePresenter.createView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        guidePresenter.viewCreated(view, savedInstanceState);
    }
}
