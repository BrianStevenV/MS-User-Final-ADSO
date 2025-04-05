package com.example.demo.config.security;

import com.example.demo.config.security.jwt.JwtEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.AUTH_CONTROLLER_POST_LOGIN;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.COUNTRY_CONTROLLER_GET_ALL_COUNTRIES;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_INFOS;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_TYPES;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.REGION_CONTROLLER_GET_ALL_REGIONS;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.USER_CONTROLLER;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.USER_CONTROLLER_GET_USER_BY_ID;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.USER_CONTROLLER_PATCH_UPDATE_USER;
import static com.example.demo.config.security.utils.ConstantsRoutesSecurity.USER_CONTROLLER_POST_CREATE_USER;
import static com.example.demo.config.security.utils.ConstantsSecurity.CUSTOMER_ROLE;
import static com.example.demo.config.security.utils.ConstantsSecurity.PROVIDER_ROLE;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> formLogin.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( request -> request
                        .requestMatchers(
                                AUTH_CONTROLLER_POST_LOGIN,
                                COUNTRY_CONTROLLER_GET_ALL_COUNTRIES,
                                REGION_CONTROLLER_GET_ALL_REGIONS,
                                PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_INFOS,
                                PAYMENT_INFO_CONTROLLER_GET_ALL_PAYMENT_TYPES,
                                USER_CONTROLLER_POST_CREATE_USER
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, USER_CONTROLLER_GET_USER_BY_ID).hasAnyAuthority(CUSTOMER_ROLE, PROVIDER_ROLE)
                        .requestMatchers(HttpMethod.PATCH, USER_CONTROLLER_PATCH_UPDATE_USER).hasAnyAuthority(CUSTOMER_ROLE, PROVIDER_ROLE)
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
        config.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
