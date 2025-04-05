package com.example.demo.domain.api;

import com.example.demo.domain.models.value.objects.PaymentProvider;

import java.util.List;

public interface IPaymentProviderServicePort {
    List<PaymentProvider> getAllPaymentProvider();
}
