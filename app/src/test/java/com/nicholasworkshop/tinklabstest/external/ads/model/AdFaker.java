package com.nicholasworkshop.tinklabstest.external.ads.model;

import com.github.javafaker.Faker;
import com.nicholasworkshop.tinklabstest.external.content.model.Story;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by nicholas.wong on 2017/05/16.
 */
public class AdFaker {

    public static Ad fakeStory() {
        Faker faker = new Faker();
        return Ad.builder()
                .redirectUrl(faker.internet().url())
                .imageUrl(faker.internet().image())
                .build();
    }

    public static List<Ad> fakeAdList(int count) {
        List<Ad> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(fakeStory());
        }
        return list;
    }

}