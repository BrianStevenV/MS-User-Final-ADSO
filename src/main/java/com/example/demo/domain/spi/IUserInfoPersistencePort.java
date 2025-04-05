package com.example.demo.domain.spi;

public interface IUserInfoPersistencePort {
    boolean findByEmail(String email);
}
