package com.pricetracker.api.dto;

import java.math.BigDecimal;

public record TrackedItemResponse(Long id, String url, String name, BigDecimal targetPrice) {
}