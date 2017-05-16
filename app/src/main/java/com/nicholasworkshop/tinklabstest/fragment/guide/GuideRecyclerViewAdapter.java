package com.nicholasworkshop.tinklabstest.fragment.guide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nicholasworkshop.tinklabstest.R;
import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;
import com.nicholasworkshop.tinklabstest.widget.AdItemView;
import com.nicholasworkshop.tinklabstest.widget.StoryItemView;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
public class GuideRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int TYPE_AD = 0;
    private static final int TYPE_STORY = 1;

    private final LayoutInflater layoutInflater;

    private List<Ad> adList;
    private List<Story> storyList;

    @Inject
    GuideRecyclerViewAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getItemViewType(int position) {
        return position != 0 && position % 3 == 0 ? TYPE_AD : TYPE_STORY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_AD) {
            return new RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.view_item_ad, parent, false)) {
            };

        } else if (viewType == TYPE_STORY) {
            return new RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.view_item_story, parent, false)) {
            };
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
            Story story = storyList.get(position - position / 4);
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
        int adCount = (int) Math.floor(((double) (storyList != null ? storyList.size() : 1)) / 3);
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
}
