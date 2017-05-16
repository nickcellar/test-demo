package com.nicholasworkshop.tinklabstest.external.content.model;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Data
@Builder
public class Story {

    private String title;

    private String summary;

    private String featureImageUrl;
}
