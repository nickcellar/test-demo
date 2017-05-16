package com.nicholasworkshop.tinklabstest.external.content.model;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicholas.wong on 2017/05/16.
 */
public class StoryFaker {

    public static Story fakeStory() {
        Faker faker = new Faker();
        return Story.builder()
                .title(faker.name().name())
                .summary(faker.shakespeare().asYouLikeItQuote())
                .featureImageUrl(faker.internet().image())
                .build();
    }

    public static List<Story> fakeStoryList(int count) {
        List<Story> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(fakeStory());
        }
        return list;
    }
}