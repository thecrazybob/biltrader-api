package com.biltrader.api.review;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.biltrader.api.appuser.AppUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Review
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {
    @Id
    @SequenceGenerator(name = "review_sequence", sequenceName = "review_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_sequence")
    private Long id;

    private Integer stars;

    private String title;
    private String content;

    private LocalDateTime reviewTime;

    @ManyToOne
    @JoinColumn(nullable = false, name = "review_poster_id")
    private AppUser reviewerUser;

    @ManyToOne
    @JoinColumn(nullable = false, name = "review_receiver_id")
    private AppUser reviewedUser;

    public Review(int stars, String title, String content, LocalDateTime reviewTime, AppUser reviewerUser,
            AppUser reviewedUser) {
        this.stars = stars;
        this.title = title;
        this.content = content;
        this.reviewTime = reviewTime;
        this.reviewerUser = reviewerUser;
        this.reviewedUser = reviewedUser;
    }

}