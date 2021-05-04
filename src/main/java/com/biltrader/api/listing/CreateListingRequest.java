package com.biltrader.api.listing;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateListingRequest {
    private final Long ownerId;
    private final String title;
    private final String description;
    private final String imageUrl;
    private final Double price;
    private final Boolean isPublic;
    private final Integer categoryId;
}
