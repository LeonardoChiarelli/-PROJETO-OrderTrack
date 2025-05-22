package br.com.OrderTrack.Order.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInDTO(
        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
