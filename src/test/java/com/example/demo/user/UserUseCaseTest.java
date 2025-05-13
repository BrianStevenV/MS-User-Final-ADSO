package com.example.demo.user;

import com.example.demo.adapters.driving.http.handler.commands.request.CreateUserCommand;
import com.example.demo.adapters.driving.http.handler.commands.request.PatchUserCommand;
import com.example.demo.adapters.driving.http.handler.commands.request.PaymentMethodsCommand;
import com.example.demo.domain.exceptions.EmailAlreadyExistsException;
import com.example.demo.domain.exceptions.UserDisableException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.models.User;
import com.example.demo.domain.models.value.objects.Country;
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
import com.example.demo.domain.usecase.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
public class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IRolePersistencePort rolePersistencePort;
    @Mock
    private IUserInfoPersistencePort userInfoPersistencePort;
    @Mock
    private IUserPaymentPersistencePort userPaymentPersistencePort;
    @Mock
    private ICountryPersistencePort countryPersistencePort;
    @Mock
    private IRegionPersistencePort regionPersistencePort;
    @Mock
    private IPasswordEncoderPort passwordEncoderPort;
    @Mock
    private IPaymentTypePersistencePort paymentTypePersistencePort;
    @Mock
    private IPaymentProviderPersistencePort paymentProviderPersistencePort;

    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(
                userPersistencePort,
                rolePersistencePort,
                userInfoPersistencePort,
                userPaymentPersistencePort,
                countryPersistencePort,
                regionPersistencePort,
                passwordEncoderPort,
                paymentTypePersistencePort,
                paymentProviderPersistencePort
        );
    }

    @Test
    void createUserThrowsEmailAlreadyExistsExceptionWhenEmailExists() {
        CreateUserCommand command = mock(CreateUserCommand.class);
        when(userInfoPersistencePort.findByEmail(anyString())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userUseCase.createUser(command, null));
    }

    @Test
    void createUserSavesUserWhenValidDataProvided() {
        CreateUserCommand command = mock(CreateUserCommand.class);
        PaymentMethodsCommand paymentMethodsCommand = mock(PaymentMethodsCommand.class);
        when(userInfoPersistencePort.findByEmail(anyString())).thenReturn(false);
        when(rolePersistencePort.findRoleById(anyLong())).thenReturn(Optional.of(mock(Role.class)));
        when(countryPersistencePort.findCountryById(anyLong())).thenReturn(Optional.of(mock(Country.class)));
        when(regionPersistencePort.findRegionById(anyLong())).thenReturn(Optional.of(mock(Region.class)));
        when(passwordEncoderPort.encode(anyString())).thenReturn("encodedPassword");

        userUseCase.createUser(command, paymentMethodsCommand);

        verify(userPersistencePort).createUser(any(User.class));
    }

    @Test
    void getUserByIdThrowsUserNotFoundExceptionWhenUserDoesNotExist() {
        when(userPersistencePort.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userUseCase.getUserById(1L));
    }

    @Test
    void getUserByIdReturnsUserWhenUserExists() {
        User user = mock(User.class);
        when(userPersistencePort.findById(anyLong())).thenReturn(Optional.of(user));
        when(user.isActive()).thenReturn(true);

        User result = userUseCase.getUserById(1L);

        assertEquals(user, result);
    }

    @Test
    void patchUserThrowsUserDisableExceptionWhenUserIsInactive() {
        PatchUserCommand command = mock(PatchUserCommand.class);
        when(userPersistencePort.findById(anyLong())).thenReturn(Optional.of(mock(User.class)));
        when(userPersistencePort.findById(anyLong()).get().isActive()).thenReturn(false);

        assertThrows(UserDisableException.class, () -> userUseCase.patchUser(command, null));
    }

    @Test
    void patchUserUpdatesUserWhenValidDataProvided() {
        PatchUserCommand command = mock(PatchUserCommand.class);
        PaymentMethodsCommand paymentMethodsCommand = mock(PaymentMethodsCommand.class);
        User user = mock(User.class);
        when(userPersistencePort.findById(anyLong())).thenReturn(Optional.of(user));
        when(user.isActive()).thenReturn(true);
        when(countryPersistencePort.findCountryById(anyLong())).thenReturn(Optional.of(mock(Country.class)));
        when(regionPersistencePort.findRegionById(anyLong())).thenReturn(Optional.of(mock(Region.class)));
        when(passwordEncoderPort.encode(anyString())).thenReturn("encodedPassword");

        userUseCase.patchUser(command, paymentMethodsCommand);

        verify(userPersistencePort).createUser(user);
    }
}
