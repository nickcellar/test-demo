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
    public void getGuide() throws Exception {
        List<Story> storyList = contentService.getGuide().blockingFirst();
        assertEquals(102, storyList.size());
    }
}