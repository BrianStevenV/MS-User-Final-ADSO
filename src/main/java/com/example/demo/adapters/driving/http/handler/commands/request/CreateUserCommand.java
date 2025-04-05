package com.example.demo.adapters.driving.http.handler.commands.request;

import com.example.demo.domain.models.value.objects.Email;
import com.example.demo.domain.models.value.objects.Password;
import com.example.demo.domain.models.value.objects.Phone;

import java.time.LocalDateTime;

public class CreateUserCommand {
    private final String name;
    private final String surName;
    private final Email email;
    private final Phone phone;
    private final Password password;
    private final LocalDateTime birthdate;
    private final long roleId;
    private final long countryId;
    private final long regionId;

    public CreateUserCommand(String name, String surName, Email email, Phone phone, Password password, LocalDateTime birthdate, long roleId, long countryId, long regionId) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.birthdate = birthdate;
        this.roleId = roleId;
        this.countryId = countryId;
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public Email getEmail() {
        return email;
    }

    public Phone getPhone() {
        return phone;
    }

    public Password getPassword() {
        return password;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public long getRoleId() {
        return roleId;
    }

    public long getCountryId() {
        return countryId;
    }

    public long getRegionId() {
        return regionId;
    }
}
