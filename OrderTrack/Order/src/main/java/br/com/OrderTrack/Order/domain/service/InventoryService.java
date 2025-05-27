package br.com.OrderTrack.Order.domain.service;

import br.com.OrderTrack.Order.domain.dto.InventoryListDTO;
import br.com.OrderTrack.Order.domain.dto.UpdateInventoryDTO;
import br.com.OrderTrack.Order.domain.model.Inventory;
import br.com.OrderTrack.Order.domain.repository.IInventoryRepository;
import br.com.OrderTrack.Order.general.infra.exception.ValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private IInventoryRepository repository;

    public Page<InventoryListDTO> getList(Pageable pageable) {
        return repository.findAll(pageable).map(InventoryListDTO::new);
    }

    public void updateQuantity(@Valid UpdateInventoryDTO dto, String requestURL) {
        try {
            var id = Long.parseLong(dto.productIdOrNumber());
            var inventory = repository.findById(id).orElseThrow(() -> new ValidationException("Product not found"));

            doValidationsAndAddOrDecrease(inventory, requestURL, dto.quantity());
        } catch (NumberFormatException e) {
            var inventory = repository.findByProductName(dto.productIdOrNumber()).orElseThrow(() -> new ValidationException("Product not found"));

            doValidationsAndAddOrDecrease(inventory, requestURL, dto.quantity());
        }
    }

    public void doValidationsAndAddOrDecrease(Inventory inventory, String requestURL, Integer quantity) {
        if (!inventory.getProduct().isActive()) { throw new ValidationException("Product is not active"); }
        if (requestURL.equals("http://localhost:8080/orderTrack/admin/inventory/add")) { inventory.addQuantity(quantity); }
        if (requestURL.equals("http://localhost:8080/orderTrack/inventory/decrease")) { inventory.decreaseQuantity(quantity); }
    }
}
