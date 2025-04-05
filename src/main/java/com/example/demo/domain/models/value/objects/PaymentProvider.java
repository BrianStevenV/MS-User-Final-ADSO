package com.example.demo.domain.models.value.objects;

public final class PaymentProvider {
    private final Id id;
    private final String paymentProviderName;

    public PaymentProvider(Id id, String paymentProviderName) {
        this.id = id;
        this.paymentProviderName = paymentProviderName;
    }

    public Id getId() {
        return id;
    }

    public String getPaymentProviderName() {
        return paymentProviderName;
    }
}
