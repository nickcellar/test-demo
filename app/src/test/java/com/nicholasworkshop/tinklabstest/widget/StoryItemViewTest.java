package com.nicholasworkshop.tinklabstest.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.RequestManager;
import com.github.javafaker.Faker;
import com.nicholasworkshop.tinklabstest.MainApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = MainApplication.class)
public class StoryItemViewTest {

    private final Faker faker = new Faker();

    @Spy Context context = RuntimeEnvironment.application;
    @Mock TextView titleTextView;
    @Mock TextView summaryTextView;
    @Mock ImageView featureImageView;
    @Mock RequestManager requestManager;
    @Mock DrawableTypeRequest<String> drawableTypeRequest;

    private StoryItemView storyItemView;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(requestManager.load(anyString())).thenReturn(drawableTypeRequest);
        storyItemView = new StoryItemView(context);
        storyItemView.titleTextView = titleTextView;
        storyItemView.summaryTextView = summaryTextView;
        storyItemView.featureImageView = featureImageView;
        storyItemView.requestManager = requestManager;
    }

    @Test
    public void setTitle() throws Exception {
        String text = faker.shakespeare().asYouLikeItQuote();
        storyItemView.setTitle(text);
        //////
        verify(titleTextView).setText(text);
    }

    @Test
    public void setSummary() throws Exception {
        String text = faker.shakespeare().asYouLikeItQuote();
        storyItemView.setSummary(text);
        //////
        verify(summaryTextView).setText(text);
    }

    @Test
    public void setFeatureImageUrl() throws Exception {
        String text = faker.internet().image();
        storyItemView.setFeatureImageUrl(text);
        //////
        verify(requestManager).load(text);
        verify(drawableTypeRequest).into(featureImageView);
    }
}