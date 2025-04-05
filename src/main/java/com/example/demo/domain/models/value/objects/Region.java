package com.example.demo.domain.models.value.objects;

public final class Region {
    private final Id id;
    private final String regionName;
    private final Country country;

    public Region(Id id, String regionName, Country country) {
        this.id = id;
        this.regionName = regionName;
        this.country = country;
    }

    public Id getId() {
        return id;
    }

    public String getRegionName() {
        return regionName;
    }

    public Country getCountry() {
        return country;
    }
}
