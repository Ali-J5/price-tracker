package com.pricetracker.api.service;

import com.pricetracker.api.dto.PriceLogRequest;
import com.pricetracker.api.dto.TrackedItemRequest;
import com.pricetracker.api.dto.TrackedItemResponse;
import com.pricetracker.api.model.PriceLog;
import com.pricetracker.api.model.TrackedItem;
import com.pricetracker.api.repository.PriceLogRepository;
import com.pricetracker.api.repository.TrackedItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackedItemService {

    private static final Logger log = LoggerFactory.getLogger(TrackedItemService.class);
    private final TrackedItemRepository itemRepository;
    private final PriceLogRepository priceLogRepository;

    // Both repositories injected here
    public TrackedItemService(TrackedItemRepository itemRepository, PriceLogRepository priceLogRepository) {
        this.itemRepository = itemRepository;
        this.priceLogRepository = priceLogRepository;
    }

    public TrackedItemResponse createItem(TrackedItemRequest request) {
        log.info("Creating new tracked item for URL: {}", request.url());
        TrackedItem item = new TrackedItem();
        item.setUrl(request.url());
        item.setName(request.name());
        item.setTargetPrice(request.targetPrice());

        TrackedItem saved = itemRepository.save(item);
        return new TrackedItemResponse(saved.getId(), saved.getUrl(), saved.getName(), saved.getTargetPrice());
    }

    public List<TrackedItemResponse> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> new TrackedItemResponse(item.getId(), item.getUrl(), item.getName(),
                        item.getTargetPrice()))
                .collect(Collectors.toList());
    }

    // NEW LOGIC: Save a scraped price to the database
    public void addPriceLog(Long itemId, PriceLogRequest request) {
        TrackedItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));

        PriceLog priceLog = new PriceLog();
        priceLog.setTrackedItem(item);
        priceLog.setPrice(request.price());

        priceLogRepository.save(priceLog);
        log.info("Successfully logged price of ${} for item ID: {}", request.price(), itemId);
    }
}