package com.example.demo.domain.api;

import com.example.demo.domain.models.value.objects.Country;

import java.util.List;

public interface ICountryServicePort {
    List<Country> getAllCountries();
}
