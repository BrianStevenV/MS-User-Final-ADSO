package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.request.LoginRequestDto;
import com.example.demo.adapters.driving.http.dto.response.JwtResponseDto;

import java.text.ParseException;

public interface IAuthHandler {
    JwtResponseDto login(LoginRequestDto loginRequestDto);
    JwtResponseDto refresh(JwtResponseDto jwtResponseDto) throws ParseException;
}
