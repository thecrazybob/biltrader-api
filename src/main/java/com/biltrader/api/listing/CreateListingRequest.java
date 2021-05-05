package com.biltrader.api.listing;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Request class that contains the properties necessary for creating a new Listing object
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateListingRequest {
    private final Long ownerId;
    private final String title;
    private final String description;
    private final String imageUrl;
    private final String price;
    private final Boolean isPublic;
    private final Integer categoryId;
}
