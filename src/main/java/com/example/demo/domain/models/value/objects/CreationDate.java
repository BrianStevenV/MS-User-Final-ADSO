package com.example.demo.domain.models.value.objects;

import com.example.demo.domain.models.value.objects.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public final class CreationDate {
    private final LocalDateTime value;

    public CreationDate(LocalDateTime value){
        this.value = Objects.requireNonNull(value);
    }

    public static CreationDate builderCreationDate(){
        return new CreationDate(LocalDateTime.now());
    }

    public LocalDateTime getValue() {
        return value;
    }

    public String format(){
        return DateUtils.format(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreationDate that = (CreationDate) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
