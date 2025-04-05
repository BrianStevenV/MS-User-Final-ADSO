package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.RoleEntity;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {
    @Mapping(target = "id", source = "id", qualifiedByName = "mapId")
    Role toRole(RoleEntity roleEntity);

    @Named("mapId")
    default Id mapId(Long id) {
        return new Id(id);
    }
}
