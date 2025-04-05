package com.example.demo.domain.spi;

import com.example.demo.domain.models.value.objects.Region;

import java.util.List;
import java.util.Optional;

public interface IRegionPersistencePort {
    Optional<Region> findRegionById(long id);
    List<Region> findByAll();
}
