package com.wing.backend.forutonamanager.manager.Security;

import com.wing.backend.forutonamanager.Preference.CustomPreference;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    final CustomPreference customPreference;

    final CustomUserService customUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/login**","/favicon.ico","/isLogin")
                .permitAll();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/termsConditions/Terms**")
                .permitAll();

        http
                .oauth2Login()
                .userInfoEndpoint().userService(customUserService);

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl(customPreference.logoutSuccessRedirectUrl());

        http.authorizeRequests()
                .anyRequest().authenticated();

        http
                .addFilterAfter(oAuth2SPAFilter, ExceptionTranslationFilter.class);

    }



    @Bean
    public ClientRegistrationRepository realClientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.realClientRegistration());
    }

    private ClientRegistration realClientRegistration() {
        return ClientRegistration.withRegistrationId(customPreference.oauth2registrationId())
                .clientId(customPreference.oauth2clientId())
                .clientSecret(customPreference.oauth2clientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate(customPreference.oauth2RedirectUri())
                .scope("message.read", "message.write")
                .authorizationUri(customPreference.oauth2authorizationUri())
                .tokenUri(customPreference.oauth2tokenUri())
                .userInfoUri(customPreference.oauth2userInfoUri())
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName(customPreference.oauth2clientName())
                .registrationId(customPreference.oauth2registrationId())
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
