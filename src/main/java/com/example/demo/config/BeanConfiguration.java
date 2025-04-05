package com.example.demo.config;

import com.example.demo.adapters.driven.jpa.postgresql.adapters.CountryPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.PasswordEncoderAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.PaymentProviderPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.PaymentTypePostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.RegionPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.RolePostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.UserInfoPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.UserPaymentPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.adapters.UserPostgresqlAdapter;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.ICountryEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.IPaymentProviderEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.IPaymentTypeEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.IRegionEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.IRoleEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.UserEntityCascadeMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.ICountryEntityRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IPaymentProviderEntityRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IPaymentTypeEntityRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IRegionEntityRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IRoleEntityRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IUserEntityRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IUserInfoEntityRepository;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IUserPaymentEntityRepository;
import com.example.demo.domain.api.ICountryServicePort;
import com.example.demo.domain.api.IPaymentProviderServicePort;
import com.example.demo.domain.api.IPaymentTypeServicePort;
import com.example.demo.domain.api.IRegionServicePort;
import com.example.demo.domain.api.IUserServicePort;
import com.example.demo.domain.spi.ICountryPersistencePort;
import com.example.demo.domain.spi.IPasswordEncoderPort;
import com.example.demo.domain.spi.IPaymentProviderPersistencePort;
import com.example.demo.domain.spi.IPaymentTypePersistencePort;
import com.example.demo.domain.spi.IRegionPersistencePort;
import com.example.demo.domain.spi.IRolePersistencePort;
import com.example.demo.domain.spi.IUserInfoPersistencePort;
import com.example.demo.domain.spi.IUserPaymentPersistencePort;
import com.example.demo.domain.spi.IUserPersistencePort;
import com.example.demo.domain.usecase.CountryUseCase;
import com.example.demo.domain.usecase.PaymentUseCase;
import com.example.demo.domain.usecase.RegionUseCase;
import com.example.demo.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final PasswordEncoder passwordEncoder;

    private final ICountryEntityRepository countryEntityRepository;
    private final ICountryEntityMapper countryEntityMapper;

    private final IRegionEntityRepository regionEntityRepository;
    private final IRegionEntityMapper regionEntityMapper;

    private final IRoleEntityRepository roleEntityRepository;
    private final IRoleEntityMapper roleEntityMapper;

    private final IUserEntityRepository userEntityRepository;
    private final IUserInfoEntityRepository userInfoEntityRepository;
    private final IUserPaymentEntityRepository userPaymentEntityRepository;

    private final IPaymentProviderEntityRepository paymentProviderEntityRepository;
    private final IPaymentProviderEntityMapper paymentProviderEntityMapper;

    private final IPaymentTypeEntityRepository paymentTypeEntityRepository;
    private final IPaymentTypeEntityMapper paymentTypeEntityMapper;


    @Bean
    public ICountryPersistencePort countryPersistencePort(){
        return new CountryPostgresqlAdapter(countryEntityRepository, countryEntityMapper);
    }
    @Bean
    public IRegionPersistencePort regionPersistencePort(){
        return new RegionPostgresqlAdapter(regionEntityRepository, regionEntityMapper);
    }
    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RolePostgresqlAdapter(roleEntityRepository, roleEntityMapper);
    }
    @Bean
    public UserEntityCascadeMapper userEntityCascadeMapper() {
        return new UserEntityCascadeMapper();
    }
    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserPostgresqlAdapter(userEntityRepository, userEntityCascadeMapper());
    }
    @Bean
    public IUserInfoPersistencePort userInfoPersistencePort(){
        return new UserInfoPostgresqlAdapter(userInfoEntityRepository);
    }
    @Bean
    public IUserPaymentPersistencePort userPaymentPersistencePort(){
        return new UserPaymentPostgresqlAdapter(userPaymentEntityRepository);
    }
    @Bean
    public IPasswordEncoderPort passwordEncoderPort(){
        return new PasswordEncoderAdapter(passwordEncoder);
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IUserInfoPersistencePort userInfoPersistencePort, IUserPaymentPersistencePort userPaymentPersistencePort, ICountryPersistencePort countryPersistencePort, IRegionPersistencePort regionPersistencePort, IPasswordEncoderPort passwordEncoderPort, IPaymentTypePersistencePort paymentTypePersistencePort, IPaymentProviderPersistencePort paymentProviderPersistencePort){
        return new UserUseCase(userPersistencePort, rolePersistencePort, userInfoPersistencePort, userPaymentPersistencePort, countryPersistencePort, regionPersistencePort, passwordEncoderPort, paymentTypePersistencePort, paymentProviderPersistencePort);
    }

    @Bean
    public IRegionServicePort regionServicePort(){
        return new RegionUseCase(regionPersistencePort());
    }

    @Bean
    public ICountryServicePort countryServicePort(){
        return new CountryUseCase(countryPersistencePort());
    }

    @Bean
    public IPaymentProviderPersistencePort paymentProviderPersistencePort(){
        return new PaymentProviderPostgresqlAdapter(paymentProviderEntityRepository, paymentProviderEntityMapper);
    }

    @Bean
    public IPaymentTypePersistencePort paymentTypePersistencePort(){
        return new PaymentTypePostgresqlAdapter(paymentTypeEntityRepository, paymentTypeEntityMapper);
    }

    @Bean
    public IPaymentProviderServicePort paymentProviderServicePort(){
        return new PaymentUseCase(paymentProviderPersistencePort(), paymentTypePersistencePort());
    }

    @Bean
    public IPaymentTypeServicePort paymentTypeServicePort(){
        return new PaymentUseCase(paymentProviderPersistencePort(), paymentTypePersistencePort());
    }
}
