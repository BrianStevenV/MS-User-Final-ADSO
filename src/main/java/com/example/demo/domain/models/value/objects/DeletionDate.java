package com.example.demo.domain.models.value.objects;

import com.example.demo.domain.exceptions.DeletionDateAfterCreationDateException;

import java.time.LocalDateTime;

public final class DeletionDate extends AbstractDate {
    public DeletionDate(LocalDateTime value, CreationDate creationDate) {
        super(value, creationDate);
        if (!isAfterCreationDate(value)) {
            throw new DeletionDateAfterCreationDateException();
        }
    }
}
