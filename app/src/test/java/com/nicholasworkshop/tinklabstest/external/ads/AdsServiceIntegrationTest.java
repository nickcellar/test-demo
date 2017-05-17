package com.nicholasworkshop.tinklabstest.external.ads;

import com.nicholasworkshop.tinklabstest.external.ads.model.Ad;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by nicholas.wong on 2017/05/17.
 */
public class AdsServiceIntegrationTest {

    private AdsService adsService;

    @Before
    public void setUp() throws Exception {
        AdsServiceModule module = new AdsServiceModule();
        adsService = module.adsService();
    }

    @Test
    public void get() throws Exception {
        List<Ad> adList = adsService.get().blockingFirst();
        assertEquals(2, adList.size());
    }
}