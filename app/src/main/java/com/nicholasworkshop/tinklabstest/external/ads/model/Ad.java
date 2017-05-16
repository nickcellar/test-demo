package com.nicholasworkshop.tinklabstest.external.ads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

/**
 * Created by nicholas.wong on 2017/05/15.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class Ad {

    private String imageUrl;

    private String redirectUrl;
}
