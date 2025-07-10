package com.rakkau.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;

@Configuration
public class CustomDecoderConfig {

    @Bean
    public JwtDecoderFactory<ClientRegistration> jwtDecoderFactory() {
        return new JwtDecoderFactory<ClientRegistration>() {
            @Override
            public JwtDecoder createDecoder(ClientRegistration registration) {
                return new NoneAlgorithmJwtDecoder();
            }
        };
    }
}

