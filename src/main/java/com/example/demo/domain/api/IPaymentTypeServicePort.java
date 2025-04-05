package com.example.demo.domain.api;

import com.example.demo.domain.models.value.objects.PaymentType;

import java.util.List;

public interface IPaymentTypeServicePort {
    List<PaymentType> getAllPaymentType();
}
