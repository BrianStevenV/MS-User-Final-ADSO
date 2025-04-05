package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.mappers.IRegionEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IRegionEntityRepository;
import com.example.demo.domain.models.value.objects.Region;
import com.example.demo.domain.spi.IRegionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionPostgresqlAdapter implements IRegionPersistencePort {
    private final IRegionEntityRepository regionEntityRepository;
    private final IRegionEntityMapper regionEntityMapper;
    @Override
    public Optional<Region> findRegionById(long id) {
        return regionEntityRepository.findById(id).map(regionEntityMapper::toRegion);
    }

    @Override
    public List<Region> findByAll() {
        return regionEntityRepository.findAll().stream()
                .map(regionEntityMapper::toRegion)
                .collect(Collectors.toList());
    }
}
