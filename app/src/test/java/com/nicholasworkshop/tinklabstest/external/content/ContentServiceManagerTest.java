package com.nicholasworkshop.tinklabstest.external.content;

import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static com.nicholasworkshop.tinklabstest.TestUtilities.initRxSchedulers;
import static com.nicholasworkshop.tinklabstest.external.content.model.StoryFaker.fakeStoryList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/16.
 */
public class ContentServiceManagerTest {

    @Mock ContentService contentService;

    private TestObserver<List<Story>> testObserver;
    private ContentServiceManager contentServiceManager;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        initRxSchedulers();
        testObserver = TestObserver.create();
        contentServiceManager = new ContentServiceManager(contentService);
    }

    @Test
    public void getCityGuide() throws Exception {
        int count = 5;
        List<Story> storyList = fakeStoryList(10);
        when(contentService.getCityGuide()).thenReturn(Observable.just(storyList));
        contentServiceManager.getCityGuide(count).subscribe(testObserver);
        testObserver.assertValue(returnedStoryList -> {
            assertEquals(count, returnedStoryList.size());
            assertEquals(storyList.get(0), returnedStoryList.get(0));
            assertEquals(storyList.get(count - 1), returnedStoryList.get(count - 1));
            return true;
        });
    }

    @Test
    public void getEatGuide() throws Exception {
        int count = 5;
        List<Story> storyList = fakeStoryList(10);
        when(contentService.getEatGuide()).thenReturn(Observable.just(storyList));
        contentServiceManager.getEatGuide(count).subscribe(testObserver);
        testObserver.assertValue(returnedStoryList -> {
            assertEquals(count, returnedStoryList.size());
            assertEquals(storyList.get(0), returnedStoryList.get(0));
            assertEquals(storyList.get(count - 1), returnedStoryList.get(count - 1));
            return true;
        });
    }

    @Test
    public void getShopGuide() throws Exception {
        int count = 5;
        List<Story> storyList = fakeStoryList(10);
        when(contentService.getShopGuide()).thenReturn(Observable.just(storyList));
        contentServiceManager.getShopGuide(count).subscribe(testObserver);
        testObserver.assertValue(returnedStoryList -> {
            assertEquals(count, returnedStoryList.size());
            assertEquals(storyList.get(0), returnedStoryList.get(0));
            assertEquals(storyList.get(count - 1), returnedStoryList.get(count - 1));
            return true;
        });
    }
}