package com.example.demo.adapters.driving.http.handler.commands.request;

import com.example.demo.domain.models.value.objects.CardNumber;
import com.example.demo.domain.models.value.objects.ExpirationDate;

public class PaymentMethodsCommand {
    private final long paymentType;
    private final long provider;
    private final CardNumber cardNumber;
    private final ExpirationDate expirationDate;

    public PaymentMethodsCommand(long paymentType, long provider, CardNumber cardNumber, ExpirationDate expirationDate) {
        this.paymentType = paymentType;
        this.provider = provider;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public long getPaymentType() {
        return paymentType;
    }

    public long getProvider() {
        return provider;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public ExpirationDate getExpirationDate() {
        return expirationDate;
    }
}
