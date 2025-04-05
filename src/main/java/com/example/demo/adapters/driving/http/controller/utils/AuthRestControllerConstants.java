package com.example.demo.adapters.driving.http.controller.utils;

public class AuthRestControllerConstants {

    private AuthRestControllerConstants(){ throw new IllegalStateException("Utility class"); }

    public static final String AUTH_CONTROLLER_REQUEST_MAPPING = "/auth";

    public static final String AUTH_CONTROLLER_POST_LOGIN = "/login";
    public static final String AUTH_CONTROLLER_POST_REFRESH = "/refresh";
}
