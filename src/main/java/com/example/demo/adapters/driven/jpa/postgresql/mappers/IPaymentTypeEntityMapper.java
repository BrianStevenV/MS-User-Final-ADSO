package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.PaymentTypeEntity;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPaymentTypeEntityMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "mapPaymentTypeIdLongToId")
    PaymentType toPaymentType(PaymentTypeEntity paymentTypeEntity);

    @Named("mapPaymentTypeIdLongToId")
    static Id mapPaymentTypeIdLongToId(Long id) {
        return id != null ? new Id(id) : null;
    }
}
