package com.pricetracker.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "price_logs")
public class PriceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tracked_item_id", nullable = false)
    private TrackedItem trackedItem;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "scraped_at", insertable = false, updatable = false)
    private OffsetDateTime scrapedAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrackedItem getTrackedItem() {
        return trackedItem;
    }

    public void setTrackedItem(TrackedItem trackedItem) {
        this.trackedItem = trackedItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OffsetDateTime getScrapedAt() {
        return scrapedAt;
    }

    public void setScrapedAt(OffsetDateTime scrapedAt) {
        this.scrapedAt = scrapedAt;
    }
}