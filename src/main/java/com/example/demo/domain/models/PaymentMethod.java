package com.example.demo.domain.models;

import com.example.demo.domain.models.value.objects.CardNumber;
import com.example.demo.domain.models.value.objects.ExpirationDate;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.PaymentProvider;
import com.example.demo.domain.models.value.objects.PaymentType;

import java.util.Objects;

import static com.example.demo.domain.models.utils.ConstantsModels.PAYMENT_TYPE_CANNOT_BE_NULL_MESSAGE;
import static com.example.demo.domain.models.utils.ConstantsModels.PROVIDER_CANNOT_BE_NULL_MESSAGE;


public class PaymentMethod {
    private Id id;
    private PaymentType paymentType;
    private PaymentProvider provider;
    private CardNumber cardNumber;
    private ExpirationDate expirationDate;

    public PaymentMethod(Id id, PaymentType paymentType, PaymentProvider provider, CardNumber cardNumber, ExpirationDate expirationDate) {
        this.id = id;
        this.paymentType = Objects.requireNonNull(paymentType, PAYMENT_TYPE_CANNOT_BE_NULL_MESSAGE);
        this.provider = Objects.requireNonNull(provider, PROVIDER_CANNOT_BE_NULL_MESSAGE);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentProvider getProvider() {
        return provider;
    }

    public void setProvider(PaymentProvider provider) {
        this.provider = provider;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    public ExpirationDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ExpirationDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
