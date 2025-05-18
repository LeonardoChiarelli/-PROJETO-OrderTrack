package br.com.OrderTrack.Order.domain.repository;

import br.com.OrderTrack.Order.domain.model.Product;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByName(@NotBlank String nome);

    Page<Product> findAllByActiveTrue(Pageable pageable);

    @Modifying
    void deleteById(@NotNull Long id);
}
