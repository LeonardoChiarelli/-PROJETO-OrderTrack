package br.com.OrderTrack.Order.domain.service;

import br.com.OrderTrack.Order.domain.dto.CreateOrderDTO;
import br.com.OrderTrack.Order.domain.model.Order;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order creteOrder(@Valid CreateOrderDTO dto) {

    }
}
