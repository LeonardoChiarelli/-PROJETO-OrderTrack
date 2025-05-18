package br.com.OrderTrack.Order.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderedItemsDTO(
        Long id,

        @NotBlank
        String productName,

        @NotNull
        Integer quantity,

        @NotNull
        Double unitPrice
) {
}
