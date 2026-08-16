package com.pricetracker.api.controller;

import com.pricetracker.api.dto.PriceLogRequest;
import com.pricetracker.api.dto.TrackedItemRequest;
import com.pricetracker.api.dto.TrackedItemResponse;
import com.pricetracker.api.service.TrackedItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class TrackedItemController {

    private final TrackedItemService service;

    public TrackedItemController(TrackedItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TrackedItemResponse> createItem(@RequestBody TrackedItemRequest request) {
        TrackedItemResponse response = service.createItem(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrackedItemResponse>> getAllItems() {
        List<TrackedItemResponse> items = service.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{id}/prices")
    public ResponseEntity<Void> logPrice(@PathVariable Long id, @RequestBody PriceLogRequest request) {
        service.addPriceLog(id, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}