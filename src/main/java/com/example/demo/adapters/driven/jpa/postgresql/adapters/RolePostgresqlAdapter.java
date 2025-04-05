package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.entities.RoleEntity;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.IRoleEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IRoleEntityRepository;
import com.example.demo.domain.models.value.objects.Role;
import com.example.demo.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RolePostgresqlAdapter implements IRolePersistencePort {
    private final IRoleEntityRepository roleEntityRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findRoleById(long id) {
        RoleEntity roleEntity = roleEntityRepository.findById(id).orElseThrow();
        System.out.println(roleEntity);
        return roleEntityRepository.findById(id).map(roleEntityMapper::toRole);
    }
}
