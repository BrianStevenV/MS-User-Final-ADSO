package com.example.demo.domain.spi;

import com.example.demo.domain.models.value.objects.PaymentType;

import java.util.List;
import java.util.Optional;

public interface IPaymentTypePersistencePort {
    List<PaymentType> findByAll();
    Optional<PaymentType> findById(long id);
}
