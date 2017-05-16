package com.nicholasworkshop.tinklabstest.fragment.guide;

import com.nicholasworkshop.tinklabstest.external.ads.AdsServiceManager;
import com.nicholasworkshop.tinklabstest.external.content.ContentServiceManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/16.
 */
public class GuideModelTest {


    @Mock AdsServiceManager adsServiceManager;
    @Mock ContentServiceManager contentServiceManager;

    private GuideModel guideModel;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        guideModel = new GuideModel(adsServiceManager, contentServiceManager);
    }

    @Test
    public void storyList() throws Exception {
        guideModel.storyList(10);
        verify(contentServiceManager).getGuide(10);
    }

    @Test
    public void ads() throws Exception {
        guideModel.ads();
        verify(adsServiceManager).get();
    }
}