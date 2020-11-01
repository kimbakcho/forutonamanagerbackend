package com.wing.backend.forutonamanager.manager.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final static String SecretKey = "Zm9ydXRvbmF3aW5nIWZvcnV0b25hd2luZw==";

    final JwtAuthConverter jwtAuthConverter;

    final  CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    final OAuth2SPAFilter oAuth2SPAFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/login**","/favicon.ico")
                .permitAll();

        http
                .oauth2Login();


        http.authorizeRequests()
                .anyRequest().authenticated();

        http
                .addFilterAfter(oAuth2SPAFilter, ExceptionTranslationFilter.class);

    }



    @Bean
    @Profile("real")
    public ClientRegistrationRepository realClientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.realClientRegistration());
    }

    @Profile("real")
    private ClientRegistration realClientRegistration() {

        return ClientRegistration.withRegistrationId("forutonaMain")
                .clientId("forutonaMain")
                .clientSecret("forutona1020")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("https://localhost:8443/login/oauth2/code/forutonaMain")
                .scope("message.read", "message.write")
                .authorizationUri("https://forutona.thkomeet.com:8443/mAuth/oauth/authorize")
                .tokenUri("https://forutona.thkomeet.com:8443/mAuth/oauth/token")
                .userInfoUri("https://forutona.thkomeet.com:8443/mAuth/MUserInfo/Me")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName("forutonaMain")
                .registrationId("forutonaMain")
                .build();
    }

    @Bean
    @Profile("local")
    public ClientRegistrationRepository localClientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.localAuthClientRegistration());
    }

    @Profile("local")
    private ClientRegistration localAuthClientRegistration() {

        return ClientRegistration.withRegistrationId("TestAuth")
                .clientId("TestAuth")
                .clientSecret("TestAuth")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("https://localhost:8443/login/oauth2/code/TestAuth")
                .scope("message.read", "message.write")
                .authorizationUri("https://forutona.thkomeet.com:8443/mAuth/oauth/authorize")
                .tokenUri("https://forutona.thkomeet.com:8443/mAuth/oauth/token")
                .userInfoUri("https://forutona.thkomeet.com:8443/mAuth/MUserInfo/Me")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName("TestAuth")
                .registrationId("TestAuth")
                .build();
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
