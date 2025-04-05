package com.example.demo.config.security.utils;


public class ConstantsRoutesSecurity {
    private ConstantsRoutesSecurity() {
        throw new IllegalStateException("Utility class");
    }

    public static final String AUTH_CONTROLLER_POST_LOGIN = "/auth/login";
    public static final String AUTH_CONTROLLER_POST_REFRESH = "/auth/refresh";

    public static final String COUNTRY_CONTROLLER_GET_ALL_COUNTRIES = "/country";
    public static final String REGION_CONTROLLER_GET_ALL_REGIONS = "/region";

    public static final String PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_INFOS = "/payment-info/providers";
    public static final String PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_TYPES = "/payment-info/types";

    public static final String USER_CONTROLLER = "/user";
    public static final String USER_CONTROLLER_POST_CREATE_USER = "/user/";
    public static final String USER_CONTROLLER_PATCH_UPDATE_USER = "/user/{id}";
    public static final String USER_CONTROLLER_GET_USER_BY_ID = "/user/{id}";
}
