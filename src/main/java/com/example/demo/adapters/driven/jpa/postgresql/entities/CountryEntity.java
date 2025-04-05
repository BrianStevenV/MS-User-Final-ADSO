package com.example.demo.adapters.driven.jpa.postgresql.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "country")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_name")
    private String countryName;

    /**
     * Opcionalmente, si deseas mapear la relación inversa de 1:N con UserEntity,
     * donde user.country_id apunta a country.id
     */
    @OneToMany(mappedBy = "country")
    private List<UserEntity> users;
    /**
     * También si region.country_id apunta a country.id
     */
    @OneToMany(mappedBy = "country")
    private List<RegionEntity> regions;

    public CountryEntity(Long id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }
}
