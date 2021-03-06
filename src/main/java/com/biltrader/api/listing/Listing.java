package com.biltrader.api.listing;

import com.biltrader.api.appuser.AppUser;
import com.biltrader.api.listing.category.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
/**
 * Model class for the listing. Contains the properties necessary for a listing page in the frontend.
 * Contains a reference to its owner AppUser.
 */
public class Listing {

    @Id
    @SequenceGenerator(name = "listing_sequence", sequenceName = "listing_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listing_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "owner_id")
    private AppUser owner;

    private String title;
    private String description;
    private String imageUrl;
    private String price;
    private Boolean isPublic;
    private Category category;

    private Boolean isFeatured = false;

    public Listing(AppUser owner, String title, String description, String imageUrl, String price, Boolean isPublic, Category category) {
        this.owner = owner;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.isPublic = isPublic;
        this.category = category;
    }

    // lombok @Setter doesn't support enums
    public void setCategory(Category category) {
        this.category = category;
    }

}
