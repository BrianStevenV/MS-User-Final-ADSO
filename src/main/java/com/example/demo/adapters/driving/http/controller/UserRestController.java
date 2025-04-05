package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.request.CreateUserRequestDto;
import com.example.demo.adapters.driving.http.dto.request.PatchUserRequestDto;
import com.example.demo.adapters.driving.http.dto.response.UserResponseDto;
import com.example.demo.adapters.driving.http.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.adapters.driving.http.controller.utils.UserRestControllerConstants.GET_PARAMS_PATH_VARIABLE_USER_BY_ID;
import static com.example.demo.adapters.driving.http.controller.utils.UserRestControllerConstants.GET_USER_BY_ID;
import static com.example.demo.adapters.driving.http.controller.utils.UserRestControllerConstants.PATCH_PARAMS_PATH_VARIABLE_USER_BY_ID;
import static com.example.demo.adapters.driving.http.controller.utils.UserRestControllerConstants.PATCH_UPDATE_USER;
import static com.example.demo.adapters.driving.http.controller.utils.UserRestControllerConstants.POST_CREATE_USER;
import static com.example.demo.adapters.driving.http.controller.utils.UserRestControllerConstants.USER_CONTROLLER_REQUEST_MAPPING;

@RestController
@RequestMapping(USER_CONTROLLER_REQUEST_MAPPING)
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping(POST_CREATE_USER)
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        userHandler.createUser(createUserRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_USER_BY_ID)
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'PROVIDER')")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(GET_PARAMS_PATH_VARIABLE_USER_BY_ID) long id) {
        System.out.println("I'm get user by id");
        return ResponseEntity.ok(userHandler.getUserById(id));
    }

    @PatchMapping(PATCH_UPDATE_USER)
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'PROVIDER')")
    public ResponseEntity<Void> patchUser(@PathVariable(PATCH_PARAMS_PATH_VARIABLE_USER_BY_ID) long id, @Valid @RequestBody PatchUserRequestDto patchUserRequestDto) {
        userHandler.patchUser(id, patchUserRequestDto);
        return ResponseEntity.ok().build();
    }

}
