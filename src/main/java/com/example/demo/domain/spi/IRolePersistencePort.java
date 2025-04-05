package com.example.demo.domain.spi;

import com.example.demo.domain.models.value.objects.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> findRoleById(long id);
}
