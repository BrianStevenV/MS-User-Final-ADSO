package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.PaymentProviderEntity;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.PaymentProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPaymentProviderEntityMapper {
    @Mapping(target = "id", source = "id", qualifiedByName = "mapPaymentProviderIdLongToId")
    PaymentProvider toPaymentProvider(PaymentProviderEntity paymentProviderEntity);

    @Named("mapPaymentProviderIdLongToId")
    static Id mapPaymentProviderIdLongToId(Long id) {
        return id != null ? new Id(id) : null;
    }
}
