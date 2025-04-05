package com.example.demo.domain.models.value.objects.utils;

public class ConstantsValueObjects {
    private ConstantsValueObjects(){ throw new IllegalStateException("Utility class"); }

    public static final String PASSWORD_VALUE_CANNOT_BE_NULL_MESSAGE = "Password value cannot be null";
    public static final String NEW_PASSWORD_VALUE_CANNOT_BE_NULL_MESSAGE = "New password value cannot be null";

    public static final String PHONE_VALUE_CANNOT_BE_NULL_MESSAGE = "Phone value cannot be null";

    public static final String EMAIL_VALUE_CANNOT_BE_NULL_MESSAGE = "Email value cannot be null";

    public static final String CARD_NUMBER_VALUE_CANNOT_BE_NULL_MESSAGE = "Card number value cannot be null";
    public static final String EXPIRATION_DATE_VALUE_CANNOT_BE_NULL_MESSAGE = "Expiration date value cannot be null";

    public static final String PHONE_REGEX_PATTERN = "^\\+?[1-9]\\d{1,14}$";
    public static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String REGEX_REMOVE_SPACES_AND_DASHESH = "[\\s-]+";
}
