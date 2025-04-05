package com.example.demo.adapters.driving.http.dto.request;

public record PatchUserRequestDto(
        String password,
        String phone,
        long countryId,
        long regionId,
        PaymentMethodRequestDto paymentMethods
) {
}
