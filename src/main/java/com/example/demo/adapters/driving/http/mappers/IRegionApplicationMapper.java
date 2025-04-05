package com.example.demo.adapters.driving.http.mappers;

import com.example.demo.adapters.driving.http.dto.response.RegionResponseDto;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRegionApplicationMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "mapIdRegionToLong")
    @Mapping(source = "country.id", target = "countryId", qualifiedByName = "mapIdRegionToLong")
    RegionResponseDto toRegionResponseDto(Region region);

    @Named("mapIdRegionToLong")
    static Long mapIdToLong(Id id) {
        return id != null ? id.getValue() : null;
    }
}
