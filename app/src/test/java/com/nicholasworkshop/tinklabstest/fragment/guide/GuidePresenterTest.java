package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/17.
 */
public class GuidePresenterTest {

    @GuideModule.GuideType private static final int GUIDE_TYPE = 213;

    @Mock Context context;
    @Mock GuideView guideView;
    @Mock GuideModel guideModel;
    @Mock LayoutInflater layoutInflater;
    @Mock ViewGroup viewGroup;

    private GuidePresenter guidePresenter;

    @Before
    @SuppressWarnings("WrongConstant")
    public void setUp() throws Exception {
        initMocks(this);
        when(guideModel.storyList(anyInt(), anyInt())).thenReturn(Observable.empty());
        when(guideModel.ads()).thenReturn(Observable.empty());
        when(guideView.storyRequests()).thenReturn(Observable.empty());
        guidePresenter = new GuidePresenter(context, guideView, guideModel);
    }

    @Test
    public void createView() throws Exception {
        guidePresenter.createView(layoutInflater, viewGroup);
        verify(guideView).createView(layoutInflater, viewGroup);
    }

    @Test
    @SuppressWarnings("WrongConstant")
    public void viewCreated() throws Exception {
        guidePresenter.viewCreated(GUIDE_TYPE);
        verify(guideView).viewCreated(viewGroup);
        verify(guideModel).storyList(eq(GUIDE_TYPE), anyInt());
        verify(guideView).storyRequests();
        verify(guideModel).ads();
    }
}