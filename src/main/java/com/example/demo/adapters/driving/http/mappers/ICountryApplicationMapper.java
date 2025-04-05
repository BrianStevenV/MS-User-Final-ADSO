package com.example.demo.adapters.driving.http.mappers;

import com.example.demo.adapters.driving.http.dto.response.CountryResponseDto;
import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.models.value.objects.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICountryApplicationMapper {
    @Mapping(target = "id", source = "id", qualifiedByName = "mapIdCountryToLong")
    CountryResponseDto toCountryResponseDto(Country country);

    @Named("mapIdCountryToLong")
    static Long mapIdCountryToLong(Id id) {
        return id != null ? id.getValue() : null;
    }
}
