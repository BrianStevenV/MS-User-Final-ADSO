package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.response.PaymentTypeResponseDto;

import java.util.List;

public interface IPaymentTypeHandler {
    List<PaymentTypeResponseDto> getAllPaymentType();
}
