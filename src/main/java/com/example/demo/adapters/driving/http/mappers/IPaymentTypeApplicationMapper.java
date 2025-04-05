package com.example.demo.adapters.driving.http.mappers;

import com.example.demo.adapters.driving.http.dto.response.PaymentTypeResponseDto;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPaymentTypeApplicationMapper {
    @Mapping(target = "id", source = "id", qualifiedByName = "mapPaymentTypeIdToLong")
    PaymentTypeResponseDto toPaymentTypeResponseDto(PaymentType paymentType);

    @Named("mapPaymentTypeIdToLong")
    static Long mapPaymentTypeIdToLong(Id id) {
        return id != null ? id.getValue() : null;
    }
}
