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
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    final  CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    final OAuth2SPAFilter oAuth2SPAFilter;

    final CustomPreference customPreference;

    final CustomUserService customUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("SecurityConfig Config");

        http
                .csrf().disable();


        http
                .oauth2Login()
                .userInfoEndpoint().userService(customUserService);

        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl(customPreference.logoutSuccessRedirectUrl());

        http
                .authorizeRequests()
                .antMatchers("/login**","/favicon.ico","/isLogin")
                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/SPALogin")
                .authenticated();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/termsConditions/Terms**")
                .permitAll();


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
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return delegatingPasswordEncoder;
    }
}
