package com.pricetracker.api.dto;

import java.math.BigDecimal;

public record TrackedItemRequest(String url, String name, BigDecimal targetPrice) {
}