package com.example.demo.adapters.driving.http.handler.impl;

import com.example.demo.adapters.driving.http.dto.response.CountryResponseDto;
import com.example.demo.adapters.driving.http.handler.ICountryHandler;
import com.example.demo.adapters.driving.http.mappers.ICountryApplicationMapper;
import com.example.demo.domain.api.ICountryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryHandlerImpl implements ICountryHandler {
    private final ICountryServicePort countryServicePort;
    private final ICountryApplicationMapper countryMapper;
    @Override
    public List<CountryResponseDto> getAllCountries() {
        return countryServicePort.getAllCountries().stream().map(countryMapper::toCountryResponseDto).toList();
    }
}
