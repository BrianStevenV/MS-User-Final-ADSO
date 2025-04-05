package com.example.demo.domain.models.value.objects;

import com.example.demo.domain.exceptions.PasswordSameOldPasswordException;

import java.util.Objects;

import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.NEW_PASSWORD_VALUE_CANNOT_BE_NULL_MESSAGE;
import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.PASSWORD_VALUE_CANNOT_BE_NULL_MESSAGE;

public final class Password {

    private String value;

    public Password(String value){
        this.value = Objects.requireNonNull(value, PASSWORD_VALUE_CANNOT_BE_NULL_MESSAGE);
    }

    public void changePassword(String newValue){
        if(!compare(newValue)) throw new PasswordSameOldPasswordException();
        this.value = Objects.requireNonNull(newValue, NEW_PASSWORD_VALUE_CANNOT_BE_NULL_MESSAGE);
    }

    public boolean compare(String newPassword){
        return Objects.equals(this.value, newPassword);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
