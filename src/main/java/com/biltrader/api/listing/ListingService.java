package com.biltrader.api.listing;

import com.biltrader.api.appuser.*;
import com.biltrader.api.listing.category.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ListingService {
    private final AppUserRepository appUserRepository;
    private final ListingRepository listingRepository;

    public Long createListing(CreateListingRequest request) {
        Listing listing = new Listing(
            appUserRepository.findById(request.getOwnerId()).get(),
            request.getTitle(),
            request.getDescription(),
            request.getImageUrl(),
            request.getPrice(),
            request.getIsPublic(),
            Category.fromValue(request.getCategoryId())
        );
        
        listingRepository.save(listing);
        return listing.getId();
    }
    
    public Listing getListing(Long listingId) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new IllegalStateException("listing with id " + listingId + " does not exist"));
        
        return listing;
    }
    
    @Transactional
    public String updateListing(Long listingId, String title, String description,
        String imageUrl, Double price, Boolean isPublic, Integer categoryId) {

        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new IllegalStateException("listing with id " + listingId + " does not exist"));
        
        if (title != null) {
            listing.setTitle(title);
        }
        
        if (description != null) {
            listing.setDescription(description);
        }

        if (imageUrl != null) {
            listing.setImageUrl(imageUrl);
        }
        
        if (price != null) {
            listing.setPrice(price);
        }
        
        if (isPublic != null) {
            listing.setIsPublic(isPublic);
        }
        
        if (categoryId != null) {
            listing.setCategory(Category.fromValue(categoryId));
        }
        
        return "listing updated successfully";
    }
    
    @Transactional
    public String deleteListing(Long listingId) {
        boolean exists = listingRepository.existsById(listingId);

        if (exists) {
            listingRepository.deleteById(listingId);
        } else {
            throw new IllegalStateException("listing with id " + listingId + " does not exist");
        }

        return "listing deleted successfully";
    }
}
