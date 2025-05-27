package br.com.OrderTrack.Order.domain.mapper;

import java.math.BigDecimal;
import java.util.List;

public record InventoryBillingMapperRecord(BigDecimal totalBilling, List<SalesStatisticsMapperRecord> statistics) {
}
