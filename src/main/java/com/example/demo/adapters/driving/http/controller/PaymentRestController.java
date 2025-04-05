package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.response.PaymentProviderResponseDto;
import com.example.demo.adapters.driving.http.dto.response.PaymentTypeResponseDto;
import com.example.demo.adapters.driving.http.handler.IPaymentProviderHandler;
import com.example.demo.adapters.driving.http.handler.IPaymentTypeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.adapters.driving.http.controller.utils.PaymentProviderRestControllerConstants.PAYMENT_PROVIDER_REST_CONTROLLER_GET_ALL_PAYMENT_PROVIDERS;
import static com.example.demo.adapters.driving.http.controller.utils.PaymentTypeRestControllerConstants.PAYMENT_TYPE_REST_CONTROLLER_GET_ALL_PAYMENT_TYPES;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-info")
public class PaymentRestController {

    //localhost:8081/payment-info/types
    //localhost:8081/payment-info/providers

    private final IPaymentProviderHandler paymentProviderHandler;
    private final IPaymentTypeHandler paymentTypeHandler;

    @GetMapping(PAYMENT_PROVIDER_REST_CONTROLLER_GET_ALL_PAYMENT_PROVIDERS)
    public ResponseEntity<List<PaymentProviderResponseDto>> getAllPaymentProvider(){
        return ResponseEntity.ok(paymentProviderHandler.getAllPaymentProvider());
    }

    @GetMapping(PAYMENT_TYPE_REST_CONTROLLER_GET_ALL_PAYMENT_TYPES)
    public ResponseEntity<List<PaymentTypeResponseDto>> getAllPaymentType(){
        return ResponseEntity.ok(paymentTypeHandler.getAllPaymentType());
    }
}
