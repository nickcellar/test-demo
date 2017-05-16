package com.nicholasworkshop.tinklabstest.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.RequestManager;
import com.github.javafaker.Faker;
import com.nicholasworkshop.tinklabstest.MainApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = MainApplication.class)
public class AdItemViewTest {

    private final Faker faker = new Faker();

    @Spy Context context = RuntimeEnvironment.application;
    @Mock ImageView adImageView;
    @Mock RequestManager requestManager;
    @Mock DrawableTypeRequest<String> drawableTypeRequest;
    @Captor ArgumentCaptor<Intent> intentCaptor;

    private AdItemView adItemView;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(requestManager.load(anyString())).thenReturn(drawableTypeRequest);
        adItemView = new AdItemView(context);
        adItemView.requestManager = requestManager;
        adItemView.adImageView = adImageView;
    }

    @Test
    public void setImageUrl() throws Exception {
        String url = faker.internet().image();
        adItemView.setImageUrl(url);
        //////
        verify(requestManager).load(url);
        verify(drawableTypeRequest).into(adImageView);
    }

    @Test
    public void clear() throws Exception {
        adItemView.setImageUrl(faker.internet().image());
        adItemView.setRedirectUrl(faker.internet().url());
        adItemView.clear();
        //////
        verify(adImageView).setImageDrawable(null);
    }

    @Test
    public void onClicked() throws Exception {
        String url = faker.internet().url();
        adItemView.setRedirectUrl(url);
        adItemView.onClicked();
        ///////
        verify(context).startActivity(intentCaptor.capture());
        Intent intent = intentCaptor.getValue();
        assertEquals(Intent.ACTION_VIEW, intent.getAction());
        assertEquals(Intent.FLAG_ACTIVITY_NEW_TASK, intent.getFlags());
        assertEquals(url, intent.getData().toString());
    }

    @Test
    public void onClicked_whenRedirectUrlNotSet_thenNothingHappens() throws Exception {
        adItemView.onClicked();
        //////
        verify(context, never()).startActivity(any());
    }

    @Test
    public void onClicked_whenCleared_thenNothingHappens() throws Exception {
        adItemView.setImageUrl(faker.internet().image());
        adItemView.setRedirectUrl(faker.internet().url());
        adItemView.clear();
        adItemView.onClicked();
        //////
        verify(context, never()).startActivity(any());
    }
}