package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicholasworkshop.tinklabstest.MainApplication;
import com.nicholasworkshop.tinklabstest.R;
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;
import com.nicholasworkshop.tinklabstest.widget.AdItemView;
import com.nicholasworkshop.tinklabstest.widget.RecyclerViewHolder;
import com.nicholasworkshop.tinklabstest.widget.StoryItemView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static com.nicholasworkshop.tinklabstest.external.ads.model.AdFaker.fakeAdList;
import static com.nicholasworkshop.tinklabstest.external.content.model.StoryFaker.fakeStoryList;
import static com.nicholasworkshop.tinklabstest.fragment.guide.GuideRecyclerViewAdapter.TYPE_AD;
import static com.nicholasworkshop.tinklabstest.fragment.guide.GuideRecyclerViewAdapter.TYPE_STORY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = MainApplication.class)
public class GuideRecyclerViewAdapterTest {

    @Mock View view;
    @Mock ViewGroup viewGroup;
    @Mock AdItemView adItemView;
    @Mock StoryItemView storyItemView;
    @Mock LayoutInflater layoutInflater;
    @Mock RecyclerView.AdapterDataObserver observer;

    private GuideRecyclerViewAdapter guideRecyclerViewAdapter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        guideRecyclerViewAdapter = new GuideRecyclerViewAdapter(layoutInflater);

    }

    @Test
    public void getItemViewType() throws Exception {
        guideRecyclerViewAdapter.setAdOffset(4);
        guideRecyclerViewAdapter.setAdInterval(8);
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(0));
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(1));
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(2));
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(3));
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(4));
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(5));
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(6));
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(12));
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(13));
        assertEquals(TYPE_AD, guideRecyclerViewAdapter.getItemViewType(20));
        assertEquals(TYPE_STORY, guideRecyclerViewAdapter.getItemViewType(21));
    }

    @Test
    public void onCreateViewHolder_whenCreatingAd() throws Exception {
        when(layoutInflater.inflate(R.layout.view_item_ad, viewGroup, false)).thenReturn(view);
        RecyclerView.ViewHolder holder = guideRecyclerViewAdapter.onCreateViewHolder(viewGroup, TYPE_AD);
        assertEquals(view, holder.itemView);
    }

    @Test
    public void onCreateViewHolder_whenCreatingStory() throws Exception {
        when(layoutInflater.inflate(R.layout.view_item_story, viewGroup, false)).thenReturn(view);
        RecyclerView.ViewHolder holder = guideRecyclerViewAdapter.onCreateViewHolder(viewGroup, TYPE_STORY);
        assertEquals(view, holder.itemView);
    }

    @Test(expected = RuntimeException.class)
    public void onCreateViewHolder_throwExceptionIfTypeIsUnknown() throws Exception {
        guideRecyclerViewAdapter.onCreateViewHolder(viewGroup, 13254);
    }

    @Test
    public void onBindViewHolder_whenBindingStoryView() throws Exception {
        List<Story> storyList = fakeStoryList(200);
        RecyclerViewHolder holder = new RecyclerViewHolder(storyItemView);
        guideRecyclerViewAdapter.setAdOffset(4);
        guideRecyclerViewAdapter.setAdInterval(8);
        guideRecyclerViewAdapter.setStoryList(storyList);
        guideRecyclerViewAdapter.onBindViewHolder(holder, 3);
        Story story = storyList.get(3);
        verify(storyItemView).setTitle(story.getTitle());
        verify(storyItemView).setSummary(story.getSummary());
        verify(storyItemView).setFeatureImageUrl(story.getFeatureImageUrl());
    }

    @Test
    public void onBindViewHolder_whenBindingAdView() throws Exception {
        List<Ad> adList = fakeAdList(1);
        RecyclerViewHolder holder = new RecyclerViewHolder(adItemView);
        guideRecyclerViewAdapter.setAdOffset(4);
        guideRecyclerViewAdapter.setAdInterval(8);
        guideRecyclerViewAdapter.setAdList(adList);
        guideRecyclerViewAdapter.onBindViewHolder(holder, 4);
        Ad ad = adList.get(0);
        verify(adItemView).setImageUrl(ad.getImageUrl());
        verify(adItemView).setRedirectUrl(ad.getRedirectUrl());
    }

    @Test
    public void onBindViewHolder_whenBindingAdView_clearWithNoAdsAvailable() throws Exception {
        RecyclerViewHolder holder = new RecyclerViewHolder(adItemView);
        guideRecyclerViewAdapter.setAdOffset(4);
        guideRecyclerViewAdapter.setAdInterval(8);
        guideRecyclerViewAdapter.onBindViewHolder(holder, 4);
        verify(adItemView).clear();
    }

    @Test
    public void getItemCount() throws Exception {
        guideRecyclerViewAdapter.setAdOffset(4);
        guideRecyclerViewAdapter.setAdInterval(8);
        guideRecyclerViewAdapter.setStoryList(fakeStoryList(200));
        assertEquals(225, guideRecyclerViewAdapter.getItemCount());
    }

    @Test
    public void setStoryList() throws Exception {
        guideRecyclerViewAdapter.registerAdapterDataObserver(observer);
        guideRecyclerViewAdapter.setStoryList(fakeStoryList(200));
        verify(observer).onChanged();
    }

    @Test
    public void setAdList() throws Exception {
        guideRecyclerViewAdapter.registerAdapterDataObserver(observer);
        guideRecyclerViewAdapter.setAdList(fakeAdList(200));
        verify(observer).onChanged();
    }

    @Test
    public void getStoryListIndex() throws Exception {
        guideRecyclerViewAdapter.setAdOffset(4);
        guideRecyclerViewAdapter.setAdInterval(8);
        assertEquals(0, guideRecyclerViewAdapter.getStoryListIndex(0));
        assertEquals(1, guideRecyclerViewAdapter.getStoryListIndex(1));
        assertEquals(2, guideRecyclerViewAdapter.getStoryListIndex(2));
        assertEquals(3, guideRecyclerViewAdapter.getStoryListIndex(3));
        assertEquals(4, guideRecyclerViewAdapter.getStoryListIndex(5));
        assertEquals(5, guideRecyclerViewAdapter.getStoryListIndex(6));
        assertEquals(6, guideRecyclerViewAdapter.getStoryListIndex(7));
        assertEquals(7, guideRecyclerViewAdapter.getStoryListIndex(8));
        assertEquals(8, guideRecyclerViewAdapter.getStoryListIndex(9));
        assertEquals(9, guideRecyclerViewAdapter.getStoryListIndex(10));
        assertEquals(10, guideRecyclerViewAdapter.getStoryListIndex(11));
        assertEquals(11, guideRecyclerViewAdapter.getStoryListIndex(13));
        assertEquals(18, guideRecyclerViewAdapter.getStoryListIndex(21));
    }
}