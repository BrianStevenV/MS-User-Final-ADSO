package com.example.demo.domain.spi;

import com.example.demo.domain.models.value.objects.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryPersistencePort {
    Optional<Country> findCountryById(long id);

    List<Country> findByAll();
}
