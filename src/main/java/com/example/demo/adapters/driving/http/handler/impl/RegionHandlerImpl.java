package com.example.demo.adapters.driving.http.handler.impl;

import com.example.demo.adapters.driving.http.dto.response.RegionResponseDto;
import com.example.demo.adapters.driving.http.handler.IRegionHandler;
import com.example.demo.adapters.driving.http.mappers.IRegionApplicationMapper;
import com.example.demo.domain.api.IRegionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionHandlerImpl implements IRegionHandler {
    private final IRegionServicePort regionServicePort;
    private final IRegionApplicationMapper regionMapper;
    @Override
    public List<RegionResponseDto> getAllRegions() {
        return regionServicePort.getAllRegions().stream().map(regionMapper::toRegionResponseDto).toList();
    }
}
