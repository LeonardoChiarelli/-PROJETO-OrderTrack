package br.com.OrderTrack.Order.domain.controller;

import br.com.OrderTrack.Order.domain.dto.CreateOrderDTO;
import br.com.OrderTrack.Order.domain.dto.OrderDetailsDTO;
import br.com.OrderTrack.Order.domain.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<OrderDetailsDTO> creteOrder(@RequestBody @Valid CreateOrderDTO dto, UriComponentsBuilder uriBuilder){
        var order = service.creteOrder(dto);

        var uri = uriBuilder.path("{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(new OrderDetailsDTO(order));
    }
}
