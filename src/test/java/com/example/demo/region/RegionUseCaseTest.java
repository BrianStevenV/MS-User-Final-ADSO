package com.example.demo.region;

import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.Region;
import com.example.demo.domain.spi.IRegionPersistencePort;
import com.example.demo.domain.usecase.RegionUseCase;
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
class RegionUseCaseTest {

    @Mock
    private IRegionPersistencePort regionPersistencePort;

    private RegionUseCase regionUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        regionUseCase = new RegionUseCase(regionPersistencePort);
    }

    @Test
    void getAllRegionsReturnsEmptyListWhenNoRegionsExist() {
        when(regionPersistencePort.findByAll()).thenReturn(Collections.emptyList());

        List<Region> regions = regionUseCase.getAllRegions();

        assertEquals(Collections.emptyList(), regions);
    }

    @Test
    void getAllRegionsReturnsListOfRegionsWhenRegionsExist() {
        Id id1 = new Id(1L);
        String name1 = "Region A";
        Country country1 = new Country(new Id(101L), "Country X");
        Region region1 = new Region(id1, name1, country1);

        Id id2 = new Id(2L);
        String name2 = "Region B";
        Country country2 = new Country(new Id(102L), "Country Y");
        Region region2 = new Region(id2, name2, country2);

        List<Region> expectedRegions = List.of(region1, region2);
        when(regionPersistencePort.findByAll()).thenReturn(expectedRegions);

        List<Region> actualRegions = regionUseCase.getAllRegions();

        assertEquals(expectedRegions.size(), actualRegions.size());
        assertEquals(expectedRegions.get(0).getId(), actualRegions.get(0).getId());
        assertEquals(expectedRegions.get(0).getRegionName(), actualRegions.get(0).getRegionName());
        assertEquals(expectedRegions.get(0).getCountry(), actualRegions.get(0).getCountry());
        assertEquals(expectedRegions.get(1).getId(), actualRegions.get(1).getId());
        assertEquals(expectedRegions.get(1).getRegionName(), actualRegions.get(1).getRegionName());
        assertEquals(expectedRegions.get(1).getCountry(), actualRegions.get(1).getCountry());
    }
}