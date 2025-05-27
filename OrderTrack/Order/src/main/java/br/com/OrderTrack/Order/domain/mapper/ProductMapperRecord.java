package br.com.OrderTrack.Order.domain.mapper;

import br.com.OrderTrack.Order.domain.model.Product;

import java.math.BigDecimal;

public record ProductMapperRecord(Long id, String name, BigDecimal price) {
    public ProductMapperRecord(Product p){
        this(p.getId(), p.getName(), p.getPrice());
    }
}
