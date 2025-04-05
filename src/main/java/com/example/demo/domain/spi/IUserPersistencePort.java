package com.example.demo.domain.spi;

import com.example.demo.domain.models.User;

import java.util.Optional;

public interface IUserPersistencePort {

    void createUser(User user);
    Optional<User> findById(long id);
}
