package com.biltrader.api.review;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.transaction.Transactional;

import com.biltrader.api.appuser.AppUserRepository;
import com.biltrader.api.appuser.AppUserService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewService {

    AppUserService appUserService;
    AppUserRepository appUserRepository;
    ReviewRepository reviewRepository;

    // public void saveReview(Review review) {
    // reviewRepository.save(review);
    // }

    // public Optional<Review> getReview(Long id) {
    // return reviewRepository.findById(id);
    // }

    public String postReview(ReviewRequest request) {
        Review review = new Review(request.getStars(), request.getTitle(), request.getContent(), LocalDateTime.now(),
                appUserRepository.findById(request.getReviewerId()).get(),
                appUserRepository.findById(request.getReviewedId()).get());

        appUserService.addReview(appUserRepository.findById(request.getReviewedId()).get(), review);

        reviewRepository.save(review);

        return "review posted successfully";
    }

    @Transactional
    public String updateReview(Long reviewId, Integer stars, String title, String content) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalStateException("review with id " + reviewId + " does not exist"));

        if (stars != null && !Objects.equals(review.getStars(), stars)) {
            review.setStars(stars);
        }

        if (title != null && title.length() > 0 && !Objects.equals(review.getTitle(), title)) {
            review.setTitle(title);
        }

        if (content != null && content.length() > 0 && !Objects.equals(review.getContent(), content)) {
            review.setContent(content);
        }

        review.setReviewTime(LocalDateTime.now());

        return "review updated successfully";
    }

    @Transactional
    public String deteteReview(Long reviewId) {
        boolean exists = reviewRepository.existsById(reviewId);

        if (exists) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new IllegalStateException("review with id " + reviewId + " does not exist");
        }

        return "review deleted successfully";
    }
}
