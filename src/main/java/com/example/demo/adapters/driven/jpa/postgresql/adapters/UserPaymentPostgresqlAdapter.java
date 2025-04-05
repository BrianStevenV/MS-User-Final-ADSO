package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.repositories.IUserPaymentEntityRepository;
import com.example.demo.domain.spi.IUserPaymentPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class UserPaymentPostgresqlAdapter implements IUserPaymentPersistencePort {
    private final IUserPaymentEntityRepository userPaymentRepository;
}
