package com.nicholasworkshop.tinklabstest.external.ads.model

import com.github.javafaker.Faker
import com.nicholasworkshop.tinklabstest.external.content.model.Story

import java.util.ArrayList

import org.junit.Assert.*

object AdFaker {

    fun fakeStory(): Ad {
        val faker = Faker()
        return Ad(
                redirectUrl = faker.internet().url(),
                imageUrl = faker.internet().image())
    }

    fun fakeAdList(count: Int): List<Ad> {
        val list = ArrayList<Ad>()
        for (i in 0 until count) {
            list.add(fakeStory())
        }
        return list
    }

}