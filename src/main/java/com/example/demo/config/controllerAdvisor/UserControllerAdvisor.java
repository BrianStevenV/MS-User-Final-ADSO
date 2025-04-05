package com.example.demo.config.controllerAdvisor;

import com.example.demo.domain.exceptions.CountryNotFoundException;
import com.example.demo.domain.exceptions.DeletionDateAfterCreationDateException;
import com.example.demo.domain.exceptions.EmailAlreadyExistsException;
import com.example.demo.domain.exceptions.EmailInvalidFormatException;
import com.example.demo.domain.exceptions.EmptyPaymentMethodsException;
import com.example.demo.domain.exceptions.ExpirationDateInPastException;
import com.example.demo.domain.exceptions.InvalidCardNumberException;
import com.example.demo.domain.exceptions.ModificationDateAfterCreationDateException;
import com.example.demo.domain.exceptions.PasswordSameOldPasswordException;
import com.example.demo.domain.exceptions.PaymentMethodsAmountNotAllowException;
import com.example.demo.domain.exceptions.PaymentProviderNotFoundException;
import com.example.demo.domain.exceptions.PaymentTypeNotFoundException;
import com.example.demo.domain.exceptions.PhoneInvalidFormatException;
import com.example.demo.domain.exceptions.RegionNotFoundException;
import com.example.demo.domain.exceptions.UserDisableException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.relation.RoleNotFoundException;

import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.COUNTRY_NOT_FOUND;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.DELETION_DATE_AFTER_CREATION_DATE;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.EMAIL_ALREADY_EXISTS;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.EMAIL_INVALID_FORMAT;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.EMPTY_PAYMENT_METHODS;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.EXPIRATION_DATE_IN_PAST;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.INVALID_CARD_NUMBER;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.MODIFICATION_DATE_AFTER_CREATION_DATE;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.PASSWORD_SAME_OLD_PASSWORD;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.PAYMENT_METHODS_AMOUNT_NOT_ALLOW;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.PHONE_INVALID_FORMAT;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.REGION_NOT_FOUND;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.ROLE_NOT_FOUND;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.PAYMENT_PROVIDER_NOT_FOUND;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.PAYMENT_TYPE_NOT_FOUND;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.USER_DISABLE;
import static com.example.demo.config.controllerAdvisor.utils.ConstantsException.USER_NOT_FOUND;

@ControllerAdvice
public class UserControllerAdvisor {

    @ExceptionHandler(EmailInvalidFormatException.class)
    public ResponseEntity<String> handleEmailInvalidFormatException(EmailInvalidFormatException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EMAIL_INVALID_FORMAT);
    }

    @ExceptionHandler(PhoneInvalidFormatException.class)
    public ResponseEntity<String> handlePhoneInvalidFormatException(PhoneInvalidFormatException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PHONE_INVALID_FORMAT);
    }

    @ExceptionHandler(PasswordSameOldPasswordException.class)
    public ResponseEntity<String> handlePasswordSameOldPasswordException(PasswordSameOldPasswordException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PASSWORD_SAME_OLD_PASSWORD);
    }

    @ExceptionHandler(ModificationDateAfterCreationDateException.class)
    public ResponseEntity<String> handleModificationDateAfterCreationDateException(ModificationDateAfterCreationDateException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MODIFICATION_DATE_AFTER_CREATION_DATE);
    }

    @ExceptionHandler(DeletionDateAfterCreationDateException.class)
    public ResponseEntity<String> handleDeletionDateAfterCreationDateException(DeletionDateAfterCreationDateException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DELETION_DATE_AFTER_CREATION_DATE);
    }

    @ExceptionHandler(InvalidCardNumberException.class)
    public ResponseEntity<String> handleInvalidCardNumberException(InvalidCardNumberException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INVALID_CARD_NUMBER);
    }

    @ExceptionHandler(ExpirationDateInPastException.class)
    public ResponseEntity<String> handleExpirationDateInPastException(ExpirationDateInPastException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EXPIRATION_DATE_IN_PAST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ROLE_NOT_FOUND);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<String> handleCountryNotFoundException(CountryNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(COUNTRY_NOT_FOUND);
    }

    @ExceptionHandler(RegionNotFoundException.class)
    public ResponseEntity<String> handleRegionNotFoundException(RegionNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(REGION_NOT_FOUND);
    }

    @ExceptionHandler(PaymentMethodsAmountNotAllowException.class)
    public ResponseEntity<String> handlePaymentMethodsAmountNotAllowException(PaymentMethodsAmountNotAllowException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PAYMENT_METHODS_AMOUNT_NOT_ALLOW);
    }
    @ExceptionHandler(EmptyPaymentMethodsException.class)
    public ResponseEntity<String> handleEmptyPaymentMethodsException(EmptyPaymentMethodsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EMPTY_PAYMENT_METHODS);
    }
    @ExceptionHandler(PaymentProviderNotFoundException.class)
    public ResponseEntity<String> handlePaymentProviderNotFoundException(PaymentProviderNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PAYMENT_PROVIDER_NOT_FOUND);
    }
    @ExceptionHandler(PaymentTypeNotFoundException.class)
    public ResponseEntity<String> handlePaymentTypeNotFoundException(PaymentTypeNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PAYMENT_TYPE_NOT_FOUND);
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EMAIL_ALREADY_EXISTS);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(USER_NOT_FOUND);
    }
    @ExceptionHandler(UserDisableException.class)
    public ResponseEntity<String> handleUserDisableException(UserDisableException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(USER_DISABLE);
    }
}
