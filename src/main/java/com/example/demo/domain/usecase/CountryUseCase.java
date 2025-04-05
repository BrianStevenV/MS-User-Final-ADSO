package com.example.demo.domain.usecase;

import com.example.demo.domain.api.ICountryServicePort;
import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.spi.ICountryPersistencePort;

import java.util.List;

public class CountryUseCase implements ICountryServicePort {
    private final ICountryPersistencePort countryPersistencePort;

    public CountryUseCase(ICountryPersistencePort countryPersistencePort) {
        this.countryPersistencePort = countryPersistencePort;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryPersistencePort.findByAll();
    }
}
