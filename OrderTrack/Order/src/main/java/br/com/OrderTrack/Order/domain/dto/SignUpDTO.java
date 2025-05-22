package br.com.OrderTrack.Order.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpDTO(
        Long id,

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {
}
