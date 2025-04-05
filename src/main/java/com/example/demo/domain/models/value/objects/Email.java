package com.example.demo.domain.models.value.objects;

import com.example.demo.domain.exceptions.EmailInvalidFormatException;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.EMAIL_REGEX_PATTERN;
import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.EMAIL_VALUE_CANNOT_BE_NULL_MESSAGE;

public final class Email {
    private final String value;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX_PATTERN);

    public Email(String value){
        if (!isValidEmail(value)) {
            throw new EmailInvalidFormatException();
        }
        this.value = Objects.requireNonNull(value, EMAIL_VALUE_CANNOT_BE_NULL_MESSAGE);
    }
    public String getValue() {
        return value;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches(String.valueOf(EMAIL_PATTERN));
    }
}
