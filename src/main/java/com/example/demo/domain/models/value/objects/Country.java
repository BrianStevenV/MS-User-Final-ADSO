package com.example.demo.domain.models.value.objects;

public final class Country {
    private final Id id;
    private final String countryName;

    public Country(Id id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public Id getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public Long getIdValue() {
        return id != null ? id.getValue() : null;
    }
}
