package com.rakkau.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeRequests(authorizeRequest -> authorizeRequest
                .antMatchers("/", "/webjars/**", "/css/**", "/favicon.*", "/imgs/**").permitAll()
                .anyRequest().authenticated())
            .oauth2Login(oauthLogin -> oauthLogin
                        .userInfoEndpoint()
                        .oidcUserService(new OidcUserService()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Reemplazamos el JwtDecoder global
        return new NoneAlgorithmJwtDecoder();
    }

}