package com.example.demo.domain.usecase;

import com.example.demo.adapters.driving.http.handler.commands.request.CreateUserCommand;
import com.example.demo.adapters.driving.http.handler.commands.request.PatchUserCommand;
import com.example.demo.adapters.driving.http.handler.commands.request.PaymentMethodsCommand;
import com.example.demo.domain.api.IUserServicePort;
import com.example.demo.domain.exceptions.CountryNotFoundException;
import com.example.demo.domain.exceptions.EmailAlreadyExistsException;
import com.example.demo.domain.exceptions.EmptyPaymentMethodsException;
import com.example.demo.domain.exceptions.PaymentProviderNotFoundException;
import com.example.demo.domain.exceptions.PaymentTypeNotFoundException;
import com.example.demo.domain.exceptions.RegionNotFoundException;
import com.example.demo.domain.exceptions.RoleNotFoundException;
import com.example.demo.domain.exceptions.UserDisableException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.PaymentMethod;
import com.example.demo.domain.models.User;
import com.example.demo.domain.models.value.objects.Country;
import com.example.demo.domain.models.value.objects.ModificationDate;
import com.example.demo.domain.models.value.objects.Password;
import com.example.demo.domain.models.value.objects.PaymentProvider;
import com.example.demo.domain.models.value.objects.PaymentType;
import com.example.demo.domain.models.value.objects.Region;
import com.example.demo.domain.models.value.objects.Role;
import com.example.demo.domain.spi.ICountryPersistencePort;
import com.example.demo.domain.spi.IPasswordEncoderPort;
import com.example.demo.domain.spi.IPaymentProviderPersistencePort;
import com.example.demo.domain.spi.IPaymentTypePersistencePort;
import com.example.demo.domain.spi.IRegionPersistencePort;
import com.example.demo.domain.spi.IRolePersistencePort;
import com.example.demo.domain.spi.IUserInfoPersistencePort;
import com.example.demo.domain.spi.IUserPaymentPersistencePort;
import com.example.demo.domain.spi.IUserPersistencePort;


import java.time.LocalDateTime;

import static com.example.demo.domain.models.value.objects.CreationDate.builderCreationDate;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IUserInfoPersistencePort userInfoPersistencePort;
    private final IUserPaymentPersistencePort userPaymentPersistencePort;
    private final ICountryPersistencePort countryPersistencePort;
    private final IRegionPersistencePort regionPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IPaymentTypePersistencePort paymentTypePersistencePort;
    private final IPaymentProviderPersistencePort paymentProviderPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IUserInfoPersistencePort userInfoPersistencePort, IUserPaymentPersistencePort userPaymentPersistencePort, ICountryPersistencePort countryPersistencePort, IRegionPersistencePort regionPersistencePort, IPasswordEncoderPort passwordEncoderPort, IPaymentTypePersistencePort paymentTypePersistencePort, IPaymentProviderPersistencePort paymentProviderPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.userInfoPersistencePort = userInfoPersistencePort;
        this.userPaymentPersistencePort = userPaymentPersistencePort;
        this.countryPersistencePort = countryPersistencePort;
        this.regionPersistencePort = regionPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.paymentTypePersistencePort = paymentTypePersistencePort;
        this.paymentProviderPersistencePort = paymentProviderPersistencePort;
    }

    @Override
    public void createUser(CreateUserCommand createUserCommand, PaymentMethodsCommand paymentMethodsCommand) {
        boolean existsEmail = userInfoPersistencePort.findByEmail(createUserCommand.getEmail().getValue());
        if(existsEmail){
            throw new EmailAlreadyExistsException();
        }
        User user = buildUser(createUserCommand);
        user.setPaymentMethods(createPaymentMethods(paymentMethodsCommand));
        saveUser(user);
    }

    @Override
    public User getUserById(long id) {
        User user = findByIdUser(id);
        isEnable(user);
        return user;
    }

    @Override
    public void patchUser(PatchUserCommand patchUserCommand, PaymentMethodsCommand paymentMethodsCommand) {
        User user = findByIdUser(patchUserCommand.getId());
        isEnable(user);

        Country country = countryPersistencePort.findCountryById(patchUserCommand.getCountryId())
                .orElseThrow(CountryNotFoundException::new);

        Region region = regionPersistencePort.findRegionById(patchUserCommand.getRegionId())
                .orElseThrow(RegionNotFoundException::new);

        Password password = new Password(passwordEncoderPort.encode(patchUserCommand.getPassword().getValue()));

        user.setCountry(country);
        user.setRegion(region);
        user.setPassword(password);
        user.setUpdatedAt(new ModificationDate(LocalDateTime.now(), user.getCreatedAt()));
        user.setPaymentMethods(new PaymentMethod(
                user.getPaymentMethods().getId(),
                findByIdPaymentType(paymentMethodsCommand.getPaymentType()),
                findByIdPaymentProvider(paymentMethodsCommand.getProvider()),
                paymentMethodsCommand.getCardNumber(),
                paymentMethodsCommand.getExpirationDate()
        ));

        saveUser(user);

    }

    private void saveUser(User user) {
        userPersistencePort.createUser(user);
    }

    private PaymentMethod createPaymentMethods(PaymentMethodsCommand paymentMethodsCommand) {
        if (paymentMethodsCommand == null) {
            throw new EmptyPaymentMethodsException();
        }
        return new PaymentMethod(
                null,
                findByIdPaymentType(paymentMethodsCommand.getPaymentType()),
                findByIdPaymentProvider(paymentMethodsCommand.getProvider()),
                paymentMethodsCommand.getCardNumber(),
                paymentMethodsCommand.getExpirationDate()
        );
    }

    private User buildUser(CreateUserCommand createUserCommand) {

        Role role = rolePersistencePort.findRoleById(createUserCommand.getRoleId())
                .orElseThrow(RoleNotFoundException::new);
        Country country = countryPersistencePort.findCountryById(createUserCommand.getCountryId())
                .orElseThrow(CountryNotFoundException::new);

        Region region = regionPersistencePort.findRegionById(createUserCommand.getRegionId())
                .orElseThrow(RegionNotFoundException::new);

        Password password = new Password(passwordEncoderPort.encode(createUserCommand.getPassword().getValue()));

        User user = new User();
        user.setName(createUserCommand.getName());
        user.setSurname(createUserCommand.getSurName());
        user.activate();
        user.setEmail(createUserCommand.getEmail());
        user.setPhone(createUserCommand.getPhone());
        user.setPassword(password);
        user.setBirthDate(createUserCommand.getBirthdate());
        user.setRole(role);
        user.setCountry(country);
        user.setRegion(region);
        user.setCreatedAt(builderCreationDate());
        return user;
    }


    private PaymentProvider findByIdPaymentProvider(long id){
        return paymentProviderPersistencePort.findById(id)
                .orElseThrow(PaymentProviderNotFoundException::new);
    }

    private PaymentType findByIdPaymentType(long id){
        return paymentTypePersistencePort.findById(id)
                .orElseThrow(PaymentTypeNotFoundException::new);
    }

    private User findByIdUser(long id) {
        return userPersistencePort.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    private boolean isEnable(User user){
        if(!user.isActive()){
            throw new UserDisableException();
        }
        return true;
    }
}
