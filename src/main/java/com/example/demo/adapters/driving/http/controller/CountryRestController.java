package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.response.CountryResponseDto;
import com.example.demo.adapters.driving.http.handler.ICountryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.adapters.driving.http.controller.utils.CountryRestControllerConstants.COUNTRY_CONTROLLER_REQUEST_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(COUNTRY_CONTROLLER_REQUEST_MAPPING)
public class CountryRestController {
    private final ICountryHandler countryHandler;

    @GetMapping
    public ResponseEntity<List<CountryResponseDto>> getAllCountries(){
        return ResponseEntity.ok(countryHandler.getAllCountries());
    }
}
