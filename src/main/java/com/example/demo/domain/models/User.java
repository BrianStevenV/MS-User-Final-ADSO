package com.example.demo.domain.models;

import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.models.value.objects.CreationDate;
import com.example.demo.domain.models.value.objects.DeletionDate;
import com.example.demo.domain.models.value.objects.Email;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.ModificationDate;
import com.example.demo.domain.models.value.objects.Password;
import com.example.demo.domain.models.value.objects.Phone;
import com.example.demo.domain.models.value.objects.Region;
import com.example.demo.domain.models.value.objects.Role;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.example.demo.domain.models.utils.ConstantsModels.NAME_CANNOT_BE_NULL_MESSAGE;
import static com.example.demo.domain.models.utils.ConstantsModels.SURNAME_CANNOT_BE_NULL_MESSAGE;

public class User {
    private Id id;
    private String name;
    private String surname;
    private boolean active;

    private Email email;
    private Phone phone;
    private Password password;
    private LocalDateTime birthDate;

    private CreationDate createdAt;
    private ModificationDate updatedAt;
    private DeletionDate deletedAt;

    private Role role;
    private Country country;
    private Region region;
    private PaymentMethod paymentMethods;

    public User(){};
    public User(Id id, String name, String surname, boolean active, Email email, Phone phone, Password password, LocalDateTime birthDate, CreationDate createdAt, ModificationDate updatedAt, DeletionDate deletedAt, Role role, Country country, Region region, PaymentMethod paymentMethods) {
        this.id = id;
        this.name = Objects.requireNonNull(name, NAME_CANNOT_BE_NULL_MESSAGE);
        this.surname = Objects.requireNonNull(surname, SURNAME_CANNOT_BE_NULL_MESSAGE);
        this.active = active;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.role = role;
        this.country = country;
        this.region = region;
        this.paymentMethods = paymentMethods;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
        this.deletedAt = new DeletionDate(LocalDateTime.now(), this.createdAt);
    }

    public void changeEmail(Email email) {
        this.email = email;
        this.updatedAt = new ModificationDate(LocalDateTime.now(), this.createdAt);
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public CreationDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreationDate createdAt) {
        this.createdAt = createdAt;
    }

    public ModificationDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ModificationDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DeletionDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(DeletionDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public PaymentMethod getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethod paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
