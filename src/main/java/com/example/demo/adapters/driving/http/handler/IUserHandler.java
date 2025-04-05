package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.request.CreateUserRequestDto;
import com.example.demo.adapters.driving.http.dto.request.PatchUserRequestDto;
import com.example.demo.adapters.driving.http.dto.response.UserResponseDto;

public interface IUserHandler {
    void createUser(CreateUserRequestDto createUserRequestDto);
    UserResponseDto getUserById(long id);
    void patchUser(long id, PatchUserRequestDto patchUserRequestDto);
}
