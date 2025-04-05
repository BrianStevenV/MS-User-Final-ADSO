package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.request.LoginRequestDto;
import com.example.demo.adapters.driving.http.dto.response.JwtResponseDto;
import com.example.demo.adapters.driving.http.handler.IAuthHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import static com.example.demo.adapters.driving.http.controller.utils.AuthRestControllerConstants.AUTH_CONTROLLER_POST_LOGIN;
import static com.example.demo.adapters.driving.http.controller.utils.AuthRestControllerConstants.AUTH_CONTROLLER_POST_REFRESH;
import static com.example.demo.adapters.driving.http.controller.utils.AuthRestControllerConstants.AUTH_CONTROLLER_REQUEST_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_CONTROLLER_REQUEST_MAPPING)
public class AuthRestController {
    private final IAuthHandler authHandler;

    @PostMapping(AUTH_CONTROLLER_POST_LOGIN)
    public ResponseEntity<JwtResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authHandler.login(loginRequestDto), HttpStatus.OK);
    }
    @PostMapping(AUTH_CONTROLLER_POST_REFRESH)
    public ResponseEntity<JwtResponseDto> refresh(@RequestBody JwtResponseDto jwtResponseDto) throws ParseException {
        return new ResponseEntity<>(authHandler.refresh(jwtResponseDto), HttpStatus.OK);
    }
}
