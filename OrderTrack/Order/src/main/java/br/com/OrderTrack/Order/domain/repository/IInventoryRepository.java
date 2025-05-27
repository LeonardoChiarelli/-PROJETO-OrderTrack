package br.com.OrderTrack.Order.domain.repository;

import br.com.OrderTrack.Order.domain.model.Inventory;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IInventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductName(@NotBlank String productName);
}
