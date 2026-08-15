package com.pricetracker.api.service;

import com.pricetracker.api.dto.TrackedItemRequest;
import com.pricetracker.api.dto.TrackedItemResponse;
import com.pricetracker.api.model.TrackedItem;
import com.pricetracker.api.repository.TrackedItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackedItemService {

    private static final Logger log = LoggerFactory.getLogger(TrackedItemService.class);
    private final TrackedItemRepository repository;

    public TrackedItemService(TrackedItemRepository repository) {
        this.repository = repository;
    }

    public TrackedItemResponse createItem(TrackedItemRequest request) {
        log.info("Creating new tracked item for URL: {}", request.url());

        TrackedItem item = new TrackedItem();
        item.setUrl(request.url());
        item.setName(request.name());
        item.setTargetPrice(request.targetPrice());

        TrackedItem saved = repository.save(item);
        log.info("Successfully created tracked item with ID: {}", saved.getId());

        return new TrackedItemResponse(saved.getId(), saved.getUrl(), saved.getName(), saved.getTargetPrice());
    }

    public List<TrackedItemResponse> getAllItems() {
        log.info("Fetching all tracked items");
        return repository.findAll().stream()
                .map(item -> new TrackedItemResponse(item.getId(), item.getUrl(), item.getName(),
                        item.getTargetPrice()))
                .collect(Collectors.toList());
    }
}