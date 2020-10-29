package com.wing.backend.forutonamanager.manager.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final static String SecretKey = "Zm9ydXRvbmF3aW5nIWZvcnV0b25hd2luZw==";

    final JwtAuthConverter jwtAuthConverter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http.oauth2ResourceServer((oauth2)->{
            oauth2.jwt((x)->{
              x.jwtAuthenticationConverter(jwtAuthConverter);
            });
        });

    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtDecoder customDecoder() {
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(new SecretKeySpec(SecretKey.getBytes(), "HMACSHA256"))
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
        return decoder;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return delegatingPasswordEncoder;
    }
}
