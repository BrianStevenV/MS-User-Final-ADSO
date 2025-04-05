package com.example.demo.adapters.driving.http.dto.request;

import java.time.LocalDateTime;

public record CreateUserRequestDto(
        String name,
        String surname,

        String email,
        String phone,
        String password,
        LocalDateTime birthdate,

        long countryId,
        long regionId,
        PaymentMethodRequestDto paymentMethods,
        long roleId
) {
}
