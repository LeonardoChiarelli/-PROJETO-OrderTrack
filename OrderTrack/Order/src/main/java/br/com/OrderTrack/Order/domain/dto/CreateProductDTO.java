package br.com.OrderTrack.Order.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductDTO(
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        String category,

        @NotNull
        BigDecimal price
) {
}
