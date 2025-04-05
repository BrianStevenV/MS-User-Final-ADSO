package com.example.demo.domain.spi;

import com.example.demo.domain.models.value.objects.PaymentProvider;

import java.util.List;
import java.util.Optional;

public interface IPaymentProviderPersistencePort {
    List<PaymentProvider> findByAll();
    Optional<PaymentProvider> findById(long id);
}
