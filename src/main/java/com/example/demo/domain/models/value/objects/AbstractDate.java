package com.example.demo.domain.models.value.objects;

import com.example.demo.domain.models.value.objects.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractDate {
    protected final LocalDateTime value;
    protected final CreationDate creationDate;

    public AbstractDate(LocalDateTime value, CreationDate creationDate) {
        this.creationDate = Objects.requireNonNull(creationDate);
        this.value = Objects.requireNonNull(value);
    }

    protected boolean isAfterCreationDate(LocalDateTime date) {
        return date.isAfter(creationDate.getValue());
    }

    public LocalDateTime getValue() {
        return value;
    }

    public String format() {
        return DateUtils.format(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDate that = (AbstractDate) o;
        return Objects.equals(value, that.value) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, creationDate);
    }
}
