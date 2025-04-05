package com.example.demo.adapters.driving.http.handler.commands.request;

import com.example.demo.domain.models.value.objects.Password;
import com.example.demo.domain.models.value.objects.Phone;

public class PatchUserCommand {
    private final long id;
    private final Password password;
    private final Phone phone;
    private final long countryId;
    private final long regionId;

    public PatchUserCommand(long id, Password password, Phone phone, long countryId, long regionId) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.countryId = countryId;
        this.regionId = regionId;
    }

    public long getId() {
        return id;
    }

    public Password getPassword() {
        return password;
    }

    public Phone getPhone() {
        return phone;
    }

    public long getCountryId() {
        return countryId;
    }

    public long getRegionId() {
        return regionId;
    }
}
