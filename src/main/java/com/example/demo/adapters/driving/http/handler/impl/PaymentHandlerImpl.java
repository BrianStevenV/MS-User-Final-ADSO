package com.example.demo.adapters.driving.http.handler.impl;

import com.example.demo.adapters.driving.http.dto.response.PaymentProviderResponseDto;
import com.example.demo.adapters.driving.http.dto.response.PaymentTypeResponseDto;
import com.example.demo.adapters.driving.http.handler.IPaymentProviderHandler;
import com.example.demo.adapters.driving.http.handler.IPaymentTypeHandler;
import com.example.demo.adapters.driving.http.mappers.IPaymentProviderApplicationMapper;
import com.example.demo.adapters.driving.http.mappers.IPaymentTypeApplicationMapper;
import com.example.demo.domain.api.IPaymentProviderServicePort;
import com.example.demo.domain.api.IPaymentTypeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PaymentHandlerImpl implements IPaymentProviderHandler, IPaymentTypeHandler {

    private final IPaymentProviderServicePort paymentProviderServicePort;
    private final IPaymentTypeServicePort paymentTypeServicePort;

    private final IPaymentProviderApplicationMapper paymentProviderApplicationMapper;
    private final IPaymentTypeApplicationMapper paymentTypeApplicationMapper;

    @Override
    public List<PaymentProviderResponseDto> getAllPaymentProvider() {
        return paymentProviderServicePort.getAllPaymentProvider()
                .stream()
                .map(paymentProviderApplicationMapper::toPaymentProviderResponseDto)
                .toList();
    }

    @Override
    public List<PaymentTypeResponseDto> getAllPaymentType() {
        return paymentTypeServicePort.getAllPaymentType()
                .stream()
                .map(paymentTypeApplicationMapper::toPaymentTypeResponseDto)
                .toList();
    }
}
