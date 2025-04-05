package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CountryEntity;
import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.models.value.objects.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICountryEntityMapper {
    @Mapping(target = "id", source = "id", qualifiedByName = "mapCountryId")
    Country toCountry(CountryEntity countryEntity);

    @Named("mapCountryId")
    default Id mapId(Long id) {
        return new Id(id);
    }
}
