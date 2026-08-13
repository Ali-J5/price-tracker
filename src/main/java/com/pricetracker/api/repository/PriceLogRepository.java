package com.pricetracker.api.repository;

import com.pricetracker.api.model.PriceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceLogRepository extends JpaRepository<PriceLog, Long> {
}