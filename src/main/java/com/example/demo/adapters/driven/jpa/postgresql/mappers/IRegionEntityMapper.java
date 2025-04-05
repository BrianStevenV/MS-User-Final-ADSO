package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.RegionEntity;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = ICountryEntityMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRegionEntityMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "mapRegionId")
    @Mapping(target = "country", source = "country")
    Region toRegion(RegionEntity regionEntity);

    @Named("mapRegionId")
    default Id mapRegionId(Long id) {
        return new Id(id);
    }
}
