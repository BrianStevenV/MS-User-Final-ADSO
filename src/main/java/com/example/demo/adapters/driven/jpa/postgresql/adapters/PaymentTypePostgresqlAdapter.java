package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.mappers.IPaymentTypeEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IPaymentTypeEntityRepository;
import com.example.demo.domain.models.value.objects.PaymentType;
import com.example.demo.domain.spi.IPaymentTypePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentTypePostgresqlAdapter implements IPaymentTypePersistencePort {
    private final IPaymentTypeEntityRepository paymentTypeEntityRepository;
    private final IPaymentTypeEntityMapper paymentTypeEntityMapper;

    @Override
    public List<PaymentType> findByAll() {
        return paymentTypeEntityRepository.findAll().stream()
                .map(paymentTypeEntityMapper::toPaymentType)
                .toList();
    }

    @Override
    public Optional<PaymentType> findById(long id) {
        return paymentTypeEntityRepository.findById(id)
                .map(paymentTypeEntityMapper::toPaymentType);
    }
}
