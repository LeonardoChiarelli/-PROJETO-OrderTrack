package br.com.OrderTrack.Order.domain.repository;

import br.com.OrderTrack.Order.domain.model.Inventory;
import br.com.OrderTrack.Order.domain.model.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IInventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductName(@NotBlank String productName);

    @Query("SELECT product FROM Inventory")
    Page<Product> findAllProducts(Pageable pageable);
}
