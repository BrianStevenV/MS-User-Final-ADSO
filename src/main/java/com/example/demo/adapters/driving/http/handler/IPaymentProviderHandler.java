package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.response.PaymentProviderResponseDto;

import java.util.List;

public interface IPaymentProviderHandler {
    List<PaymentProviderResponseDto> getAllPaymentProvider();
}
