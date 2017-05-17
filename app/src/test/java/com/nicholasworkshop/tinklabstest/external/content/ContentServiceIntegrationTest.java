package com.nicholasworkshop.tinklabstest.external.content;

import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by nicholas.wong on 2017/05/17.
 */
public class ContentServiceIntegrationTest {

    private ContentService contentService;

    @Before
    public void setUp() throws Exception {
        ContentServiceModule module = new ContentServiceModule();
        contentService = module.contentService();
    }

    @Test
    public void getCityGuide() throws Exception {
        List<Story> storyList = contentService.getCityGuide().blockingFirst();
        assertEquals(102, storyList.size());
    }

    @Test
    public void getShopGuide() throws Exception {
        List<Story> storyList = contentService.getShopGuide().blockingFirst();
        assertEquals(100, storyList.size());
    }

    @Test
    public void getEatGuide() throws Exception {
        List<Story> storyList = contentService.getEatGuide().blockingFirst();
        assertEquals(100, storyList.size());
    }
}