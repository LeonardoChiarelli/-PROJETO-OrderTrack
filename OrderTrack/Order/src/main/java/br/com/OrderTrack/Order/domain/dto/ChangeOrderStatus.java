package br.com.OrderTrack.Order.domain.dto;

import br.com.OrderTrack.Order.domain.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeOrderStatus(
        @NotNull
        Long id,

        @NotNull
        OrderStatus status
) {
}
