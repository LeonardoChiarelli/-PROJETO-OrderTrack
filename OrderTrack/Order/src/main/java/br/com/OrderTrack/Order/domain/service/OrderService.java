package br.com.OrderTrack.Order.domain.service;

import br.com.OrderTrack.Order.domain.dto.ChangeOrderStatus;
import br.com.OrderTrack.Order.domain.dto.CreateOrderDTO;
import br.com.OrderTrack.Order.domain.dto.OrderDetailsDTO;
import br.com.OrderTrack.Order.domain.dto.OrderedItemsDTO;
import br.com.OrderTrack.Order.domain.helper.HelperMethod;
import br.com.OrderTrack.Order.domain.model.Address;
import br.com.OrderTrack.Order.domain.model.Order;
import br.com.OrderTrack.Order.domain.model.OrderItem;
import br.com.OrderTrack.Order.domain.repository.IInventoryRepository;
import br.com.OrderTrack.Order.domain.repository.IOrderRepository;
import br.com.OrderTrack.Order.general.infra.exception.ValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private IOrderRepository repository;

    @Autowired
    private IInventoryRepository inventoryRepository;

    public Order createOrder(@Valid CreateOrderDTO dto) {

        var address = new Address(dto.shippingAddress());
        var totalPrice = getTotalPrice(dto.items());
        var orderedItems = getItems(dto.items());

        return new Order(dto, address, totalPrice, orderedItems);
    }

    public List<OrderItem> getItems(List<OrderedItemsDTO> itemsDTO){
        return itemsDTO.stream().map(item-> {
            var inventory = inventoryRepository.findByProductName(item.productName()).orElseThrow(() -> new ValidationException("Product not found"));
            var product = HelperMethod.loadProductsByName(item.productName());

            if (inventory.getQuantity() <= item.quantity() && !product.isActive()) { throw new ValidationException("Product was not active or out of stock"); }
            inventory.decreaseQuantity(item.quantity());

            return new OrderItem(item, product);
        }).collect(Collectors.toList());
    }

    public BigDecimal getTotalPrice(List<OrderedItemsDTO> itemsDTO){
        return itemsDTO.stream()
                .map(item -> item.unitPrice().multiply(BigDecimal.valueOf(item.quantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order changeStatus(@Valid ChangeOrderStatus dto) {
        var order = repository.findById(dto.id()).orElseThrow(() -> new ValidationException("Order not found"));
        order.changeStatus(dto.status());
        return order;
    }

    public Page<OrderDetailsDTO> listAllOrders(Pageable pageable) {
        return repository.findAll(pageable).map(OrderDetailsDTO::new);
    }

    public Order getOrder(Long id) {
        return repository.findById(id).orElseThrow(() -> new ValidationException("Order not found"));
    }
}

