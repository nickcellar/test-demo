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

    @Inject GuidePresenter guidePresenter;

    public static GuideFragment newInstance() {
        return new GuideFragment();
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
        return guidePresenter.createView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        guidePresenter.viewCreated(view, savedInstanceState);
    }
}
