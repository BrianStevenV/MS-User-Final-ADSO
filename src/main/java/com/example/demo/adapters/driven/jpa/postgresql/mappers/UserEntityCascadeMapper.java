package com.example.demo.adapters.driven.jpa.postgresql.mappers;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CountryEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.PaymentProviderEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.PaymentTypeEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.RegionEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.RoleEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.UserEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.UserInfoEntity;
import com.example.demo.adapters.driven.jpa.postgresql.entities.UserPaymentEntity;
import com.example.demo.domain.models.PaymentMethod;
import com.example.demo.domain.models.User;
import com.example.demo.domain.models.value.objects.CardNumber;
import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.models.value.objects.CreationDate;
import com.example.demo.domain.models.value.objects.DeletionDate;
import com.example.demo.domain.models.value.objects.Email;
import com.example.demo.domain.models.value.objects.ExpirationDate;
import com.example.demo.domain.models.value.objects.Id;
import com.example.demo.domain.models.value.objects.ModificationDate;
import com.example.demo.domain.models.value.objects.Password;
import com.example.demo.domain.models.value.objects.PaymentProvider;
import com.example.demo.domain.models.value.objects.PaymentType;
import com.example.demo.domain.models.value.objects.Phone;
import com.example.demo.domain.models.value.objects.Region;
import com.example.demo.domain.models.value.objects.Role;

public class UserEntityCascadeMapper {
    public UserEntityCascadeMapper(){};

    public static UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setSurName(user.getSurname());
        userEntity.setStatus(user.isActive());
        userEntity.setCreatedAt(user.getCreatedAt().getValue());
        userEntity.setUpdatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().getValue() : null);
        userEntity.setDeletedAt(user.getDeletedAt() != null ? user.getDeletedAt().getValue() : null);

        // Mapear UserInfoEntity
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setEmail(user.getEmail().getValue());
        userInfoEntity.setPhone(user.getPhone().getValue());
        userInfoEntity.setPassword(user.getPassword().getValue());
        userInfoEntity.setBirthDate(user.getBirthDate());

        // Relacion Inversa.
        userInfoEntity.setUser(userEntity);

        // Si existen métodos de pago, mapeamos el primero (o podrías iterar y asociarlos según tu modelo)
        if (user.getPaymentMethods() != null) {
            PaymentMethod paymentMethod = user.getPaymentMethods();
            UserPaymentEntity paymentEntity = new UserPaymentEntity();

            // Mapeo correcto de PaymentType y PaymentProvider
            paymentEntity.setPaymentType(toPaymentTypeEntity(paymentMethod.getPaymentType()));
            paymentEntity.setProvider(toPaymentProviderEntity(paymentMethod.getProvider()));

            paymentEntity.setCardNumber(paymentMethod.getCardNumber().getValue());
            paymentEntity.setExpirationDate(paymentMethod.getExpirationDate().getValue());

            // Configurar la relación bidireccional entre userInfo y payment
            userInfoEntity.setUserPayment(paymentEntity);
            paymentEntity.setUserInfo(userInfoEntity);
        }

        userEntity.setUserInfo(userInfoEntity);
        userEntity.setRole(toRoleEntity(user.getRole()));
        userEntity.setCountry(toCountryEntity(user.getCountry()));
        userEntity.setRegion(toRegionEntity(user.getRegion()));

        return userEntity;
    }

    public User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return new User(
                new Id(userEntity.getId()),
                userEntity.getName(),
                userEntity.getSurName(),
                userEntity.isStatus(),
                new Email(userEntity.getUserInfo().getEmail()),
                new Phone(userEntity.getUserInfo().getPhone()),
                new Password(userEntity.getUserInfo().getPassword()),
                userEntity.getUserInfo().getBirthDate(),
                new CreationDate(userEntity.getCreatedAt()),
                userEntity.getUpdatedAt() != null ? new ModificationDate(userEntity.getUpdatedAt(), new CreationDate(userEntity.getCreatedAt())) : null,
                userEntity.getDeletedAt() != null ? new DeletionDate(userEntity.getDeletedAt(), new CreationDate(userEntity.getCreatedAt())) : null,
                toRole(userEntity.getRole()),
                toCountry(userEntity.getCountry()),
                toRegion(userEntity.getRegion()),
                toPaymentMethod(userEntity.getUserInfo().getUserPayment())
        );
    }

    private static PaymentTypeEntity toPaymentTypeEntity(PaymentType paymentType) {
        return paymentType != null ? new PaymentTypeEntity(paymentType.getId().getValue(), paymentType.getPaymentTypeName()) : null;
    }

    private static PaymentProviderEntity toPaymentProviderEntity(PaymentProvider provider) {
        return provider != null ? new PaymentProviderEntity(provider.getId().getValue(), provider.getPaymentProviderName()) : null;
    }
    private static RoleEntity toRoleEntity(Role role) {
        return role != null ? new RoleEntity(role.getId().getValue(), role.getRoleName(), role.getRoleDescription(), role.isRoleStatus()) : null;
    }

    private static CountryEntity toCountryEntity(Country country) {
        return country != null ? new CountryEntity(country.getId().getValue(), country.getCountryName()) : null;
    }

    private static RegionEntity toRegionEntity(Region region) {
        CountryEntity countryEntity = toCountryEntity(region.getCountry());
        return region != null ? new RegionEntity(region.getId().getValue(), region.getRegionName(), countryEntity) : null;
    }




    private Role toRole(RoleEntity roleEntity) {
        return new Role(
                new Id(roleEntity.getId()),
                roleEntity.getRoleName(),
                roleEntity.getRoleDescription(),
                roleEntity.isRoleStatus()
        );
    }

    private Country toCountry(CountryEntity countryEntity) {
        return new Country(
                new Id(countryEntity.getId()),
                countryEntity.getCountryName()
        );
    }

    private Region toRegion(RegionEntity regionEntity) {
        return new Region(
                new Id(regionEntity.getId()),
                regionEntity.getRegionName(),
                toCountry(regionEntity.getCountry())
        );
    }

    private PaymentMethod toPaymentMethod(UserPaymentEntity entity) {
        return new PaymentMethod(
                new Id(entity.getId()),
                new PaymentType(new Id(entity.getPaymentType().getId()), entity.getPaymentType().getPaymentTypeName()),
                new PaymentProvider(new Id(entity.getProvider().getId()), entity.getProvider().getPaymentProviderName()),
                new CardNumber(entity.getCardNumber()),
                new ExpirationDate(entity.getExpirationDate())
        );
    }
}
