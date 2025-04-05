package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.mappers.IPaymentProviderEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IPaymentProviderEntityRepository;
import com.example.demo.domain.models.value.objects.PaymentProvider;
import com.example.demo.domain.spi.IPaymentProviderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentProviderPostgresqlAdapter implements IPaymentProviderPersistencePort {
    private final IPaymentProviderEntityRepository paymentProviderEntityRepository;
    private final IPaymentProviderEntityMapper paymentProviderEntityMapper;

    @Override
    public List<PaymentProvider> findByAll() {
        return paymentProviderEntityRepository.findAll().stream()
                .map(paymentProviderEntityMapper::toPaymentProvider)
                .toList();
    }

    @Override
    public Optional<PaymentProvider> findById(long id) {
        return paymentProviderEntityRepository.findById(id)
                .map(paymentProviderEntityMapper::toPaymentProvider);
    }
}
