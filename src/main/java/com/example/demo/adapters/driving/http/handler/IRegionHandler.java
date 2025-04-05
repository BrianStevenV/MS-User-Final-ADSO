package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.response.RegionResponseDto;

import java.util.List;

public interface IRegionHandler {
    List<RegionResponseDto> getAllRegions();
}
