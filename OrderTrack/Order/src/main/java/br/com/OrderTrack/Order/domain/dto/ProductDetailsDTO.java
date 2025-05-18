package br.com.OrderTrack.Order.domain.dto;

import br.com.OrderTrack.Order.domain.model.Product;

import java.math.BigDecimal;

public record ProductDetailsDTO(Long id, String name, String description, String category, BigDecimal price, String status) {

    public ProductDetailsDTO(Product product, String status){
        this(product.getId(), product.getName(), product.getDescription(), product.getCategory(), product.getPrice(), status);
    }
}
