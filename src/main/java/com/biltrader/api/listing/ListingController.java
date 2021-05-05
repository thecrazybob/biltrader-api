package com.biltrader.api.listing;

import com.biltrader.api.listing.category.*;

import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/listing")
@AllArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @PostMapping(path = "new")
    public Long createListing(@RequestBody CreateListingRequest request) {
        return listingService.createListing(request);
    }

    @GetMapping(path = "{listingId}")
    public Listing getListing(@PathVariable("listingId") Long listingId) {
        return listingService.getListing(listingId);
    }

    @GetMapping(path = "all")
    public List<Listing> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping(path = "category/{categoryName}")
    public List<Listing> getListingsByCategory(@PathVariable("categoryName") Category categoryName) {
        return listingService.getListingsByCategory(categoryName);
    }

    @PutMapping(path = "{listingId}")
    public String updateListing(@PathVariable("listingId") Long listingId,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) String imageUrl,
        @RequestParam(required = false) Double price,
        @RequestParam(required = false) Boolean isPublic,
        @RequestParam(required = false) Integer categoryId) {

        return listingService.updateListing(listingId, title, description, imageUrl, price, isPublic, categoryId);
    }

    @DeleteMapping(path = "{listingId}")
    public String deleteListing(@PathVariable("listingId") Long listingId) {
        return listingService.deleteListing(listingId);
    }
}
