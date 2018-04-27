package com.nicholasworkshop.tinklabstest.external.content.model

import com.github.javafaker.Faker
import java.util.*

object StoryFaker {

    fun fakeStory(): Story {
        val faker = Faker()
        return Story(
                title = faker.name().name(),
                summary = faker.shakespeare().asYouLikeItQuote(),
                featureImageUrl = faker.internet().image())
    }

    fun fakeStoryList(count: Int): List<Story> {
        val list = ArrayList<Story>()
        for (i in 0 until count) {
            list.add(fakeStory())
        }
        return list
    }
}