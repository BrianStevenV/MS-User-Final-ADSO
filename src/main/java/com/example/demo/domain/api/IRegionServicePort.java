package com.example.demo.domain.api;

import com.example.demo.domain.models.value.objects.Region;

import java.util.List;

public interface IRegionServicePort {
    List<Region> getAllRegions();
}
