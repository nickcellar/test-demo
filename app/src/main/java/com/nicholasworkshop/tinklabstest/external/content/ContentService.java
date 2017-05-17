package com.nicholasworkshop.tinklabstest.external.content;

import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
interface ContentService {

    @GET("city.json")
    Observable<List<Story>> getCityGuide();

    @GET("shop.json")
    Observable<List<Story>> getShopGuide();

    @GET("eat.json")
    Observable<List<Story>> getEatGuide();
}
