package com.example.demo.adapters.driving.http.dto.response;

import java.time.LocalDateTime;

public record UserResponseDto(
        long id,
        String name,
        String surname,
        String email,
        String phone,
        String password,
        LocalDateTime birthdate,

        CountryResponseDto country,
        RegionResponseDto region,

        PaymentTypeResponseDto paymentType,
        PaymentProviderResponseDto paymentProvider,

        String cardNumber,
        LocalDateTime expirationDate
) {
}
