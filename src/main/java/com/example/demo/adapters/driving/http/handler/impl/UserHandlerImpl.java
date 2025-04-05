package com.example.demo.adapters.driving.http.handler.impl;

import com.example.demo.adapters.driving.http.dto.request.CreateUserRequestDto;
import com.example.demo.adapters.driving.http.dto.request.PatchUserRequestDto;
import com.example.demo.adapters.driving.http.dto.response.UserResponseDto;
import com.example.demo.adapters.driving.http.handler.IUserHandler;
import com.example.demo.adapters.driving.http.handler.commands.request.CreateUserCommand;
import com.example.demo.adapters.driving.http.handler.commands.request.PatchUserCommand;
import com.example.demo.adapters.driving.http.handler.commands.request.PaymentMethodsCommand;
import com.example.demo.adapters.driving.http.mappers.ICountryApplicationMapper;
import com.example.demo.adapters.driving.http.mappers.IPaymentProviderApplicationMapper;
import com.example.demo.adapters.driving.http.mappers.IPaymentTypeApplicationMapper;
import com.example.demo.adapters.driving.http.mappers.IRegionApplicationMapper;
import com.example.demo.domain.api.IUserServicePort;
import com.example.demo.domain.models.User;
import com.example.demo.domain.models.value.objects.CardNumber;
import com.example.demo.domain.models.value.objects.Email;
import com.example.demo.domain.models.value.objects.ExpirationDate;
import com.example.demo.domain.models.value.objects.Password;
import com.example.demo.domain.models.value.objects.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;

    private final ICountryApplicationMapper countryApplicationMapper;
    private final IRegionApplicationMapper regionApplicationMapper;
    private final IPaymentProviderApplicationMapper paymentProviderApplicationMapper;
    private final IPaymentTypeApplicationMapper paymentTypeApplicationMapper;

    @Override
    @Transactional
    @Async
    public void createUser(CreateUserRequestDto createUserRequestDto) {

        Email email = new Email(createUserRequestDto.email());
        Phone phone = new Phone(createUserRequestDto.phone());
        Password password = new Password(createUserRequestDto.password());

        PaymentMethodsCommand paymentMethods = new PaymentMethodsCommand(
                createUserRequestDto.paymentMethods().paymentType(),
                createUserRequestDto.paymentMethods().paymentProvider(),
                new CardNumber(createUserRequestDto.paymentMethods().cardNumber()),
                new ExpirationDate(createUserRequestDto.paymentMethods().expirationDate())
        );

        CreateUserCommand command = new CreateUserCommand(
                createUserRequestDto.name(),
                createUserRequestDto.surname(),
                email,
                phone,
                password,
                createUserRequestDto.birthdate(),
                createUserRequestDto.roleId(),
                createUserRequestDto.countryId(),
                createUserRequestDto.regionId()
        );

        userServicePort.createUser(command, paymentMethods);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(long id) {
        User userFound = userServicePort.getUserById(id);

        return new UserResponseDto(
                userFound.getId().getValue(),
                userFound.getName(),
                userFound.getSurname(),
                userFound.getEmail().getValue(),
                userFound.getPhone().getValue(),
                userFound.getPassword().getValue(),
                userFound.getBirthDate(),
                countryApplicationMapper.toCountryResponseDto(userFound.getCountry()),
                regionApplicationMapper.toRegionResponseDto(userFound.getRegion()),
                paymentTypeApplicationMapper.toPaymentTypeResponseDto(userFound.getPaymentMethods().getPaymentType()),
                paymentProviderApplicationMapper.toPaymentProviderResponseDto(userFound.getPaymentMethods().getProvider()),
                userFound.getPaymentMethods().getCardNumber().getValue(),
                userFound.getPaymentMethods().getExpirationDate().getValue()
        );
    }

    @Override
    public void patchUser(long id, PatchUserRequestDto patchUserRequestDto) {
        Password password = new Password(patchUserRequestDto.password());
        Phone phone = new Phone(patchUserRequestDto.phone());

        PaymentMethodsCommand paymentMethods = new PaymentMethodsCommand(
                patchUserRequestDto.paymentMethods().paymentType(),
                patchUserRequestDto.paymentMethods().paymentProvider(),
                new CardNumber(patchUserRequestDto.paymentMethods().cardNumber()),
                new ExpirationDate(patchUserRequestDto.paymentMethods().expirationDate())
        );

        PatchUserCommand patchUserCommand = new PatchUserCommand(
                id,
                password,
                phone,
                patchUserRequestDto.countryId(),
                patchUserRequestDto.regionId()
        );

        userServicePort.patchUser(patchUserCommand, paymentMethods);
    }
}
