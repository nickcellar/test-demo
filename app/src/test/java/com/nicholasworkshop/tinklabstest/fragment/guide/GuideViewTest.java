package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/17.
 */
public class GuideViewTest {

    @Mock GuideRecyclerViewAdapter guideRecyclerViewAdapter;
    @Mock RecyclerView recyclerView;
    @Mock List<Story> storyList;
    @Mock List<Ad> adList;
    @Mock View view;
    @Mock LinearLayoutManager linearLayoutManager;
    @Captor ArgumentCaptor<GuideView.GuideInfiniteScrollListener> guideInfiniteScrollListenerArgumentCaptor;

    private final TestObserver<Integer> testObserver = TestObserver.create();

    private GuideView guideView;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        guideView = new GuideView(guideRecyclerViewAdapter);
        guideView.storiesRecyclerView = recyclerView;
        guideView.layoutManager = linearLayoutManager;
    }

    @Test
    public void viewCreated() throws Exception {
        guideView.viewCreated(view);
        verify(recyclerView).setHasFixedSize(true);
        verify(recyclerView).setLayoutManager(any());
        verify(recyclerView).setAdapter(guideRecyclerViewAdapter);
        verify(recyclerView).addOnScrollListener(any());
    }

    @Test
    public void setStoryList() throws Exception {
        guideView.setStoryList(storyList);
        verify(guideRecyclerViewAdapter).setStoryList(storyList);
    }

    @Test
    public void setAdList() throws Exception {
        guideView.setAdList(adList);
        verify(guideRecyclerViewAdapter).setAdList(adList);
    }

    @Test
    public void storyRequests() throws Exception {
        guideView.viewCreated(view);
        guideView.storyRequests().subscribe(testObserver);
        when(linearLayoutManager.findLastVisibleItemPosition()).thenReturn(1234);
        verify(recyclerView).addOnScrollListener(guideInfiniteScrollListenerArgumentCaptor.capture());
        guideInfiniteScrollListenerArgumentCaptor.getValue().onScrolledToEnd(9999);
        testObserver.assertValue(1234);
    }
}