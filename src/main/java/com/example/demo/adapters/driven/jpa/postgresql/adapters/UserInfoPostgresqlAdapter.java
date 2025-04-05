package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.repositories.IUserInfoEntityRepository;
import com.example.demo.domain.spi.IUserInfoPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class UserInfoPostgresqlAdapter implements IUserInfoPersistencePort {
    private final IUserInfoEntityRepository userInfoRepository;

    @Override
    public boolean findByEmail(String email) {
        return userInfoRepository.findByEmail(email).isPresent();
    }
}
