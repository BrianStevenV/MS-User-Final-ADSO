package com.example.demo.config.security;

import com.example.demo.adapters.driven.jpa.postgresql.adapters.UserDetailsServiceImpl;
import com.example.demo.config.security.jwt.JwtProvider;
import com.example.demo.config.security.jwt.utils.JwtMethodUtils;
import com.example.demo.config.security.utils.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.AUTH_CONTROLLER_POST_LOGIN;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.COUNTRY_CONTROLLER_GET_ALL_COUNTRIES;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_INFOS;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_TYPES;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.REGION_CONTROLLER_GET_ALL_REGIONS;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.USER_CONTROLLER;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.USER_CONTROLLER_POST_CREATE_USER;
import static com.example.demo.config.security.utils.SecurityUtils.getToken;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private List<String> excludedPrefixes = Arrays.asList(
            AUTH_CONTROLLER_POST_LOGIN,
            COUNTRY_CONTROLLER_GET_ALL_COUNTRIES,
            REGION_CONTROLLER_GET_ALL_REGIONS,
            PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_INFOS,
            PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_TYPES,
            USER_CONTROLLER_POST_CREATE_USER
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getToken(request);

        if(token != null && jwtProvider.validateToken(token)){

            String username = JwtMethodUtils.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String currentRoute = request.getServletPath();
        return SecurityUtils.isExcludedPrefixRecursively(currentRoute, excludedPrefixes);
    }
}
