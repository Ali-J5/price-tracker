package com.pricetracker.api.repository;

import com.pricetracker.api.model.TrackedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackedItemRepository extends JpaRepository<TrackedItem, Long> {
}