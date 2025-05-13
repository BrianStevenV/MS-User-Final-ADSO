package com.example.demo.domain.usecase;

import com.example.demo.domain.api.ICountryServicePort;
import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.spi.ICountryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
class CountryUseCaseTest {

    @Mock
    private ICountryPersistencePort countryPersistencePort;

    private CountryUseCase countryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        countryUseCase = new CountryUseCase(countryPersistencePort);
    }

    @Test
    void getAllCountriesReturnsEmptyListWhenNoCountriesExist() {
        when(countryPersistencePort.findByAll()).thenReturn(Collections.emptyList());

        List<Country> countries = countryUseCase.getAllCountries();

        assertEquals(Collections.emptyList(), countries);
    }

    @Test
    void getAllCountriesReturnsListOfCountriesWhenCountriesExist() {
        Id id1 = new Id(1L);
        String name1 = "Country A";
        Country country1 = new Country(id1, name1);

        Id id2 = new Id(2L);
        String name2 = "Country B";
        Country country2 = new Country(id2, name2);

        List<Country> expectedCountries = List.of(country1, country2);
        when(countryPersistencePort.findByAll()).thenReturn(expectedCountries);

        List<Country> actualCountries = countryUseCase.getAllCountries();

        assertEquals(expectedCountries.size(), actualCountries.size());
        assertEquals(expectedCountries.get(0).getId(), actualCountries.get(0).getId());
        assertEquals(expectedCountries.get(0).getCountryName(), actualCountries.get(0).getCountryName());
        assertEquals(expectedCountries.get(1).getId(), actualCountries.get(1).getId());
        assertEquals(expectedCountries.get(1).getCountryName(), actualCountries.get(1).getCountryName());
    }
}