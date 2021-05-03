package com.biltrader.api.review;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public String postReview(@RequestBody ReviewRequest request) {
        return reviewService.postReview(request);
    }

    @PutMapping(path = "{reviewId}")
    public String updateReview(@PathVariable("reviewId") Long reviewId, @RequestParam(required = true) Integer stars,
            @RequestParam(required = false) String title, @RequestParam(required = false) String content) {

        return reviewService.updateReview(reviewId, stars, title, content);
    }

    @DeleteMapping(path = "{reviewId}")
    public String deleteReview(@PathVariable("reviewId") Long reviewId) {
        return reviewService.deteteReview(reviewId);
    }
}
