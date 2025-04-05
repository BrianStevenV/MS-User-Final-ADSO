package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.mappers.ICountryEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.ICountryEntityRepository;
import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.spi.ICountryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CountryPostgresqlAdapter implements ICountryPersistencePort {
    private final ICountryEntityRepository countryEntityRepository;
    private final ICountryEntityMapper countryEntityMapper;

    @Override
    public Optional<Country> findCountryById(long id) {
        return countryEntityRepository.findById(id).map(countryEntityMapper::toCountry);
    }

    @Override
    public List<Country> findByAll() {
        return countryEntityRepository.findAll().stream()
                .map(countryEntityMapper::toCountry)
                .collect(Collectors.toList());
    }
}
