package com.rakkau.config;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import com.fasterxml.jackson.core.JsonProcessingException;


import java.time.Instant;
import java.util.Map;

public class NoneAlgorithmJwtDecoder implements JwtDecoder {
    @Override
    public Jwt decode(String token) throws JwtException {
        // Decodifica sin verificar firma
        System.out.println("--------------------------------------");
        System.out.println(token);
        System.out.println("--------------------------------------");

        System.out.println("Nuevo token: " + token.split("\\.").length);

        String[] parts = token.split("\\.");
        //if (parts.length != 3) throw new JwtException("Invalid token 999999");
        if (parts.length != 2) throw new JwtException("Invalid token 999999");

        String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));
        Map<String, Object> claims = null;
        try{
            claims = new com.fasterxml.jackson.databind.ObjectMapper().readValue(payloadJson, Map.class);
        } catch (Exception e) {
            throw new JwtException("Invalid token payload", e);
        }

        return new Jwt(token, Instant.now(), Instant.now().plusSeconds(300), Map.of("alg", "none"), claims);
    }
}