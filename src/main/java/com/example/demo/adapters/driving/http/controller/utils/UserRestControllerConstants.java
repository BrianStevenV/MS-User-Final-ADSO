package com.example.demo.adapters.driving.http.controller.utils;

public class UserRestControllerConstants {
    private UserRestControllerConstants(){ throw new IllegalStateException("Utility class"); }

    public static final String USER_CONTROLLER_REQUEST_MAPPING = "/user";
    public static final String POST_CREATE_USER = "/";
    public static final String PATCH_UPDATE_USER = "/{id}";
    public static final String GET_USER_BY_ID = "/{id}";
    public static final String GET_PARAMS_PATH_VARIABLE_USER_BY_ID = "id";
    public static final String PATCH_PARAMS_PATH_VARIABLE_USER_BY_ID = "id";
}
