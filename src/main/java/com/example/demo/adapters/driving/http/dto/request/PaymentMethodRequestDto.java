package com.example.demo.adapters.driving.http.dto.request;

import java.time.LocalDateTime;

public record PaymentMethodRequestDto(
        long paymentType,
        long paymentProvider,
        String cardNumber,
        LocalDateTime expirationDate
) {
}
