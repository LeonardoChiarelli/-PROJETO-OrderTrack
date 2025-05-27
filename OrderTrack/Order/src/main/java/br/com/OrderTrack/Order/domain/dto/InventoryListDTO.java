package br.com.OrderTrack.Order.domain.dto;

import br.com.OrderTrack.Order.domain.model.Inventory;

public record InventoryListDTO(Long productId, String productName, Integer quantity) {
    public InventoryListDTO(Inventory inventory){
        this(inventory.getProduct().getId(), inventory.getProduct().getName(), inventory.getQuantity());
    }
}
