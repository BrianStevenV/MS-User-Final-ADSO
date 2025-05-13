package com.example.demo.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateUserRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String surname,

        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        String password,
        LocalDateTime birthdate,

        @NotNull
        long countryId,
        @NotNull
        long regionId,
        PaymentMethodRequestDto paymentMethods,
        @NotNull
        long roleId
) {
}
