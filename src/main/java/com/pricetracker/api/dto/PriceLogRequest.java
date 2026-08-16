package com.pricetracker.api.dto;

import java.math.BigDecimal;

public record PriceLogRequest(BigDecimal price) {
}