package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.mappers.UserEntityCascadeMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IUserEntityRepository;
import com.example.demo.domain.models.User;
import com.example.demo.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
public class UserPostgresqlAdapter implements IUserPersistencePort {
    private final IUserEntityRepository userRepository;
    private final UserEntityCascadeMapper userEntityCascadeMapper;

    @Override
    public void createUser(User user) {
        userRepository.save(userEntityCascadeMapper.toUserEntity(user));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return userRepository.findById(id).map(userEntityCascadeMapper::toUser);
    }
}
