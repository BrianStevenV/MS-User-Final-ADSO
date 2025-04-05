package com.example.demo.config.security.jwt.utils;

import com.example.demo.config.security.jwt.JwtConfig;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtMethodUtils {

    private static JwtConfig jwtConfig;

    public JwtMethodUtils(JwtConfig jwtConfig) {
        JwtMethodUtils.jwtConfig = jwtConfig;
    }

    public static String getUsernameFromToken(String token) {
        String secret = jwtConfig.getSecret();
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }
}
