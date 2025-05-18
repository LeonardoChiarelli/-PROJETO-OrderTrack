package br.com.OrderTrack.Order.domain.dto;

import br.com.OrderTrack.Order.domain.model.Product;

import java.math.BigDecimal;

public record ListOfProductsDTO(Long id, String name, String category, BigDecimal price) {
    public ListOfProductsDTO(Product product){
        this(product.getId(), product.getName(), product.getCategory(), product.getPrice());
    }
}
