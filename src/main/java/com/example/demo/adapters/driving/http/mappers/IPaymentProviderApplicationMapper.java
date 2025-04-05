package com.example.demo.adapters.driving.http.mappers;

import com.example.demo.adapters.driving.http.dto.response.PaymentProviderResponseDto;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.PaymentProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPaymentProviderApplicationMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "mapPaymentProviderIdToLong")
    PaymentProviderResponseDto toPaymentProviderResponseDto(PaymentProvider paymentProvider);

    @Named("mapPaymentProviderIdToLong")
    static Long mapPaymentProviderIdToLong(Id id) {
        return id != null ? id.getValue() : null;
    }
}
