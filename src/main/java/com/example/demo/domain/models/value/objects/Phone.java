package com.example.demo.domain.models.value.objects;

import com.example.demo.domain.exceptions.PhoneInvalidFormatException;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.PHONE_REGEX_PATTERN;
import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.PHONE_VALUE_CANNOT_BE_NULL_MESSAGE;

public final class Phone {
    private final String value;
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX_PATTERN);

    public Phone(String value){
        if (!isValidPhone(value)) {
            throw new PhoneInvalidFormatException();
        }
        this.value = Objects.requireNonNull(value, PHONE_VALUE_CANNOT_BE_NULL_MESSAGE);
    }
    public String getValue() {
        return value;
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches(String.valueOf(PHONE_PATTERN));
    }
}
