package com.example.demo.domain.usecase;

import com.example.demo.domain.api.IPaymentProviderServicePort;
import com.example.demo.domain.api.IPaymentTypeServicePort;
import com.example.demo.domain.models.value.objects.PaymentProvider;
import com.example.demo.domain.models.value.objects.PaymentType;
import com.example.demo.domain.spi.IPaymentProviderPersistencePort;
import com.example.demo.domain.spi.IPaymentTypePersistencePort;

import java.util.List;

public class PaymentUseCase implements IPaymentProviderServicePort, IPaymentTypeServicePort {
    private final IPaymentProviderPersistencePort paymentProviderPersistencePort;
    private final IPaymentTypePersistencePort paymentTypePersistencePort;

    public PaymentUseCase(IPaymentProviderPersistencePort paymentProviderPersistencePort, IPaymentTypePersistencePort paymentTypePersistencePort) {
        this.paymentProviderPersistencePort = paymentProviderPersistencePort;
        this.paymentTypePersistencePort = paymentTypePersistencePort;
    }

    @Override
    public List<PaymentProvider> getAllPaymentProvider() {
        return paymentProviderPersistencePort.findByAll();
    }

    @Override
    public List<PaymentType> getAllPaymentType() {
        return paymentTypePersistencePort.findByAll();
    }
}
