package com.example.demo.config.security.jwt;

import com.example.demo.adapters.driven.jpa.postgresql.entities.PrincipalUser;
import com.example.demo.adapters.driving.http.dto.response.JwtResponseDto;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static com.example.demo.config.security.jwt.utils.ConstantsJwt.CLAIM_KEY_ID;
import static com.example.demo.config.security.jwt.utils.ConstantsJwt.CLAIM_KEY_ROLES;
import static com.example.demo.config.security.jwt.utils.ConstantsJwt.EMPTY_JWT_TOKEN_ERROR_MESSAGE;
import static com.example.demo.config.security.jwt.utils.ConstantsJwt.EXPIRED_JWT_TOKEN_ERROR_MESSAGE;
import static com.example.demo.config.security.jwt.utils.ConstantsJwt.MALFORMED_JWT_TOKEN_ERROR_MESSAGE;
import static com.example.demo.config.security.jwt.utils.ConstantsJwt.SIGNATURE_JWT_TOKEN_ERROR_MESSAGE;
import static com.example.demo.config.security.jwt.utils.ConstantsJwt.UNSUPPORTED_JWT_TOKEN_ERROR_MESSAGE;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

import org.springframework.security.core.GrantedAuthority;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(Authentication authentication) {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principalUser.getUsername())
                .claim(CLAIM_KEY_ROLES, principalUser.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList())
                .claim(CLAIM_KEY_ID, principalUser.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 180L))
                .signWith(hmacShaKeyFor(jwtConfig.getSecret().getBytes()))
                .compact();
    }

    public String refreshToken(JwtResponseDto jwtResponseDto) throws ParseException {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .build()
                    .parseClaimsJws(jwtResponseDto.token());
        } catch (ExpiredJwtException e) {
            JWTClaimsSet claims = JWTParser.parse(jwtResponseDto.token()).getJWTClaimsSet();
            return Jwts.builder()
                    .setSubject(claims.getSubject())
                    .claim(CLAIM_KEY_ROLES, claims.getStringListClaim(CLAIM_KEY_ROLES))
                    .claim(CLAIM_KEY_ID, claims.getStringListClaim(CLAIM_KEY_ID))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 180))
                    .signWith(hmacShaKeyFor(jwtConfig.getSecret().getBytes()))
                    .compact();
        }
        return jwtResponseDto.token();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error(MALFORMED_JWT_TOKEN_ERROR_MESSAGE);
        } catch (UnsupportedJwtException e) {
            logger.error(UNSUPPORTED_JWT_TOKEN_ERROR_MESSAGE);
        } catch (ExpiredJwtException e) {
            logger.error(EXPIRED_JWT_TOKEN_ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            logger.error(EMPTY_JWT_TOKEN_ERROR_MESSAGE);
        } catch (SignatureException e) {
            logger.error(SIGNATURE_JWT_TOKEN_ERROR_MESSAGE);
        }
        return false;
    }
}
