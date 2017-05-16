package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nicholasworkshop.tinklabstest.R;
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;
import com.nicholasworkshop.tinklabstest.widget.AdItemView;
import com.nicholasworkshop.tinklabstest.widget.RecyclerViewHolder;
import com.nicholasworkshop.tinklabstest.widget.StoryItemView;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
public class GuideRecyclerViewAdapter extends RecyclerView.Adapter {

    static final int TYPE_AD = 0;
    static final int TYPE_STORY = 1;

    private final LayoutInflater layoutInflater;

    private int adOffset = 1;
    private int adInterval = 5;
    private List<Ad> adList;
    private List<Story> storyList;

    @Inject
    GuideRecyclerViewAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getItemViewType(int position) {
        return position % adInterval == adOffset ? TYPE_AD : TYPE_STORY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_AD) {
            return new RecyclerViewHolder(layoutInflater.inflate(R.layout.view_item_ad, parent, false));

        } else if (viewType == TYPE_STORY) {
            return new RecyclerViewHolder(layoutInflater.inflate(R.layout.view_item_story, parent, false));
        }
        throw new RuntimeException();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_AD) {
            AdItemView view = (AdItemView) holder.itemView;
            if (adList != null && adList.size() > 0) {
                int index = new Random().nextInt(adList.size());
                Ad ad = adList.get(index);
                view.setImageUrl(ad.getImageUrl());
                view.setRedirectUrl(ad.getRedirectUrl());
            } else {
                view.clear();
            }
            return;
        } else if (viewType == TYPE_STORY) {
            Story story = storyList.get(getStoryListIndex(position));
            StoryItemView view = (StoryItemView) holder.itemView;
            view.setTitle(story.getTitle());
            view.setSummary(story.getSummary());
            view.setFeatureImageUrl(story.getFeatureImageUrl());
            return;
        }
        throw new RuntimeException();
    }

    @Override
    public int getItemCount() {
        int storyCount = storyList != null ? storyList.size() : 0;
        int adCount = storyCount / adInterval;
        return storyCount + adCount;
    }

    void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
        notifyDataSetChanged();
    }

    void setAdList(List<Ad> adList) {
        this.adList = adList;
        notifyDataSetChanged();
    }

    /**
     * Offset for an ads to start with.
     * i.e. Setting this to 5, ads will start
     * only after the 5th item
     */
    void setAdOffset(int adOffset) {
        this.adOffset = adOffset;
    }

    /**
     * Position interval for ads to show.
     * i.e. By setting this to 5, ads will be shown
     * every 5th position, after the offset in
     * the beginning
     */
    void setAdInterval(int adInterval) {
        this.adInterval = adInterval;
    }

    /**
     * Translate the list position to the
     * actual position in the story list.
     */
    int getStoryListIndex(int position) {
        if (position < adOffset) {
            return position;
        } else {
            int offset = (position + adOffset - 1) / adInterval;
            return position - offset;
        }
    }
}
