package br.com.OrderTrack.Order.domain.dto;

import br.com.OrderTrack.Order.domain.model.Address;
import br.com.OrderTrack.Order.domain.model.Order;
import br.com.OrderTrack.Order.domain.model.OrderItem;
import br.com.OrderTrack.Order.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDetailsDTO(Long id, String consumerName, String consumerEmail, Address shippingAddress, LocalDateTime orderDate, OrderStatus status, List<OrderItem> items) {

    public OrderDetailsDTO(Order order){
        this(order.getId(), order.getConsumerName(), order.getConsumerEmail(), order.getShippingAddress(), order.getOrderDate(), order.getStatus(), order.getItems());
    }
}
