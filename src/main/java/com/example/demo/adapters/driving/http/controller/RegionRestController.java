package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.response.RegionResponseDto;
import com.example.demo.adapters.driving.http.handler.IRegionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.adapters.driving.http.controller.utils.RegionRestControllerConstants.REGION_CONTROLLER_REQUEST_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(REGION_CONTROLLER_REQUEST_MAPPING)
public class RegionRestController {
    private final IRegionHandler regionHandler;

    @GetMapping
    public ResponseEntity<List<RegionResponseDto>> getAllRegions(){
        return ResponseEntity.ok(regionHandler.getAllRegions());
    }
}
