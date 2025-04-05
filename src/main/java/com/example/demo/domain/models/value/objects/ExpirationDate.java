package com.example.demo.domain.models.value.objects;

import com.example.demo.domain.exceptions.ExpirationDateInPastException;
import com.example.demo.domain.models.value.objects.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Objects;

import static com.example.demo.domain.models.value.objects.utils.ConstantsValueObjects.EXPIRATION_DATE_VALUE_CANNOT_BE_NULL_MESSAGE;

public class ExpirationDate {
    private final LocalDateTime value;

    public ExpirationDate(LocalDateTime value) {
        this.value = Objects.requireNonNull(value, EXPIRATION_DATE_VALUE_CANNOT_BE_NULL_MESSAGE);
        if (isExpired(value)) {
            throw new ExpirationDateInPastException();
        }
    }

    public LocalDateTime getValue() {
        return value;
    }

    public String format() {
        return DateUtils.format(value);
    }

    private boolean isExpired(LocalDateTime expirationDate) {
        LocalDateTime now = LocalDateTime.now();
        YearMonth expirationMonth = YearMonth.from(expirationDate);
        LocalDateTime lastDayOfMonth = expirationMonth.atEndOfMonth().atTime(23, 59, 59);

        return now.isAfter(lastDayOfMonth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpirationDate that = (ExpirationDate) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
