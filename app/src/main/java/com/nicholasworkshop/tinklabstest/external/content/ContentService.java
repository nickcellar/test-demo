package com.nicholasworkshop.tinklabstest.external.content;

import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by nicholas.wong on 2017/05/15.
 */

public interface ContentService {

    @GET("guide.json")
    Observable<List<Story>> getGuide();
}
