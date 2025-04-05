package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.response.CountryResponseDto;

import java.util.List;

public interface ICountryHandler {

    List<CountryResponseDto> getAllCountries();
}
