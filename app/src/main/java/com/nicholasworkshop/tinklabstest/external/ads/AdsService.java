package com.nicholasworkshop.tinklabstest.external.ads;

import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

public interface AdsService {

    @GET("all.json")
    Observable<Story> getAll();
}