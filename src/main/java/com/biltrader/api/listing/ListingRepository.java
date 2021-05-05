package com.biltrader.api.listing;

import com.biltrader.api.listing.category.*;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ListingRepository extends JpaRepository<Listing, Long> {
    Optional<Listing> findById(Long id);
    
    @Query("SELECT a FROM Listing a")
    List<Listing> getAllListings();
    
    List<Listing> findByCategory(Category category);
}
