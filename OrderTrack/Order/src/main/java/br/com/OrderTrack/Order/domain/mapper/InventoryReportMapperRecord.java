package br.com.OrderTrack.Order.domain.mapper;

import org.springframework.data.domain.Page;

public record InventoryReportMapperRecord(Page<ProductMapperRecord> productMapperRecordPage){
}
