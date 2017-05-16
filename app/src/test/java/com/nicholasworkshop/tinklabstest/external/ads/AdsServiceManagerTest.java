package com.nicholasworkshop.tinklabstest.external.ads;

import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static com.nicholasworkshop.tinklabstest.TestUtilities.initRxSchedulers;
import static com.nicholasworkshop.tinklabstest.external.ads.model.AdFaker.fakeAdList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicholas.wong on 2017/05/16.
 */
public class AdsServiceManagerTest {

    @Mock AdsService adsService;

    private TestObserver<List<Ad>> testObserver;
    private AdsServiceManager adsServiceManager;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        initRxSchedulers();
        testObserver = TestObserver.create();
        adsServiceManager = new AdsServiceManager(adsService);

    }

    @Test
    public void get() throws Exception {
        List<Ad> storyList = fakeAdList(100);
        when(adsService.get()).thenReturn(Observable.just(storyList));
        adsServiceManager.get().subscribe(testObserver);
        testObserver.assertValue(returnedStoryList -> {
            assertEquals(storyList, returnedStoryList);
            return true;
        });
    }
}