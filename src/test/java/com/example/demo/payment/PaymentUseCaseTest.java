package com.example.demo.payment;

import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.PaymentProvider;
import com.example.demo.domain.models.value.objects.PaymentType;
import com.example.demo.domain.spi.IPaymentProviderPersistencePort;
import com.example.demo.domain.spi.IPaymentTypePersistencePort;
import com.example.demo.domain.usecase.PaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
class PaymentUseCaseTest {

    @Mock
    private IPaymentProviderPersistencePort paymentProviderPersistencePort;

    @Mock
    private IPaymentTypePersistencePort paymentTypePersistencePort;

    private PaymentUseCase paymentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentUseCase = new PaymentUseCase(paymentProviderPersistencePort, paymentTypePersistencePort);
    }

    @Test
    void getAllPaymentProviderReturnsEmptyListWhenNoProvidersExist() {
        when(paymentProviderPersistencePort.findByAll()).thenReturn(Collections.emptyList());

        List<PaymentProvider> providers = paymentUseCase.getAllPaymentProvider();

        assertEquals(Collections.emptyList(), providers);
    }

    @Test
    void getAllPaymentProviderReturnsListOfProvidersWhenProvidersExist() {
        Id id1 = new Id(1L);
        String name1 = "Visa";
        PaymentProvider provider1 = new PaymentProvider(id1, name1);

        Id id2 = new Id(2L);
        String name2 = "MasterCard";
        PaymentProvider provider2 = new PaymentProvider(id2, name2);

        List<PaymentProvider> expectedProviders = List.of(provider1, provider2);
        when(paymentProviderPersistencePort.findByAll()).thenReturn(expectedProviders);

        List<PaymentProvider> actualProviders = paymentUseCase.getAllPaymentProvider();

        assertEquals(expectedProviders.size(), actualProviders.size());
        assertEquals(expectedProviders.get(0).getId(), actualProviders.get(0).getId());
        assertEquals(expectedProviders.get(0).getPaymentProviderName(), actualProviders.get(0).getPaymentProviderName());
        assertEquals(expectedProviders.get(1).getId(), actualProviders.get(1).getId());
        assertEquals(expectedProviders.get(1).getPaymentProviderName(), actualProviders.get(1).getPaymentProviderName());
    }

    @Test
    void getAllPaymentTypeReturnsEmptyListWhenNoTypesExist() {
        when(paymentTypePersistencePort.findByAll()).thenReturn(Collections.emptyList());

        List<PaymentType> types = paymentUseCase.getAllPaymentType();

        assertEquals(Collections.emptyList(), types);
    }

    @Test
    void getAllPaymentTypeReturnsListOfTypesWhenTypesExist() {
        Id id1 = new Id(1L);
        String name1 = "Credit Card";
        PaymentType type1 = new PaymentType(id1, name1);

        Id id2 = new Id(2L);
        String name2 = "Debit Card";
        PaymentType type2 = new PaymentType(id2, name2);

        List<PaymentType> expectedTypes = List.of(type1, type2);
        when(paymentTypePersistencePort.findByAll()).thenReturn(expectedTypes);

        List<PaymentType> actualTypes = paymentUseCase.getAllPaymentType();

        assertEquals(expectedTypes.size(), actualTypes.size());
        assertEquals(expectedTypes.get(0).getId(), actualTypes.get(0).getId());
        assertEquals(expectedTypes.get(0).getPaymentTypeName(), actualTypes.get(0).getPaymentTypeName());
        assertEquals(expectedTypes.get(1).getId(), actualTypes.get(1).getId());
        assertEquals(expectedTypes.get(1).getPaymentTypeName(), actualTypes.get(1).getPaymentTypeName());
    }
}