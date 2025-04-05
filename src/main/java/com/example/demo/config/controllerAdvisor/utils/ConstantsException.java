package com.example.demo.config.controllerAdvisor.utils;

public class ConstantsException {
    private ConstantsException(){ throw new IllegalStateException("Utility class"); }

    public static final String EMAIL_INVALID_FORMAT = "Email invalid format";
    public static final String PHONE_INVALID_FORMAT = "Phone invalid format";
    public static final String PASSWORD_SAME_OLD_PASSWORD = "The new password must be different from the old password";
    public static final String MODIFICATION_DATE_AFTER_CREATION_DATE = "Modification date is after creation date";
    public static final String DELETION_DATE_AFTER_CREATION_DATE = "Deletion date is after creation date";
    public static final String INVALID_CARD_NUMBER = "Invalid card number";
    public static final String EXPIRATION_DATE_IN_PAST = "Expiration date cannot be in the past";
    public static final String ROLE_NOT_FOUND = "Role not found";
    public static final String COUNTRY_NOT_FOUND = "Country not found";
    public static final String REGION_NOT_FOUND = "Region not found";
    public static final String PAYMENT_METHODS_AMOUNT_NOT_ALLOW = "Payment methods amount not allow";
    public static final String EMPTY_PAYMENT_METHODS = "Empty payment methods";
    public static final String PAYMENT_PROVIDER_NOT_FOUND = "Payment provider not found";
    public static final String PAYMENT_TYPE_NOT_FOUND = "Payment type not found";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_DISABLE = "User is disable";
}
