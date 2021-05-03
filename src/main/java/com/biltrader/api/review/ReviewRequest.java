package com.biltrader.api.review;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ReviewRequest {
    private Long reviewerId;
    private Long reviewedId;

    private int stars;

    private String title;
    private String content;
}
