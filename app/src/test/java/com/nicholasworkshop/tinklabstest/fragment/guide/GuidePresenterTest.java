package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/17.
 */
public class GuidePresenterTest {

    @Mock GuideView guideView;
    @Mock GuideModel guideModel;
    @Mock LayoutInflater layoutInflater;
    @Mock ViewGroup viewGroup;

    private GuidePresenter guidePresenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(guideModel.storyList(anyInt())).thenReturn(Observable.empty());
        when(guideModel.ads()).thenReturn(Observable.empty());
        when(guideView.storyRequests()).thenReturn(Observable.empty());
        guidePresenter = new GuidePresenter(guideView, guideModel);
    }

    @Test
    public void createView() throws Exception {
        guidePresenter.createView(layoutInflater, viewGroup, null);
        verify(guideView).createView(layoutInflater, viewGroup);
    }

    @Test
    public void viewCreated() throws Exception {
        guidePresenter.viewCreated(viewGroup, null);
        verify(guideView).viewCreated(viewGroup);
        verify(guideModel).storyList(anyInt());
        verify(guideView).storyRequests();
        verify(guideModel).ads();
    }
}