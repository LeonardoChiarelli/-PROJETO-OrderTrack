package br.com.OrderTrack.Order.domain.mapper;

import java.math.BigDecimal;

public record SalesStatisticsMapperRecord(String category, Long salesQuantity, BigDecimal billing) {
}
