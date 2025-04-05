package com.example.demo.domain.models.value.objects;

public final class PaymentType {
    private final Id id;
    private final String paymentTypeName;

    public PaymentType(Id id, String paymentTypeName) {
        this.id = id;
        this.paymentTypeName = paymentTypeName;
    }

    public Id getId() {
        return id;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }
}
