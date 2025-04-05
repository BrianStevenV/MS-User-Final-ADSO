package com.example.demo.domain.usecase;

import com.example.demo.domain.api.IRegionServicePort;
import com.example.demo.domain.models.value.objects.Region;
import com.example.demo.domain.spi.IRegionPersistencePort;

import java.util.List;

public class RegionUseCase implements IRegionServicePort {
    private final IRegionPersistencePort regionPersistencePort;

    public RegionUseCase(IRegionPersistencePort regionPersistencePort) {
        this.regionPersistencePort = regionPersistencePort;
    }

    @Override
    public List<Region> getAllRegions() {
        return regionPersistencePort.findByAll();
    }
}
