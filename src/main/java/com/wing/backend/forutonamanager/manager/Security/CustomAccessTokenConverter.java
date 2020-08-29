package com.wing.backend.forutonamanager.manager.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Autowired
    UserInfoDetailService userInfoDetailService;

    @Autowired
    UserAuthenticationConverter userTokenConverter;


    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        super.setUserTokenConverter(userTokenConverter);
        OAuth2Authentication authentication = super.extractAuthentication(claims);
        return authentication;
    }

    @Bean
    public UserAuthenticationConverter getUserTokenConverter() {
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(userInfoDetailService);
        UserAuthenticationConverter userTokenConverter = defaultUserAuthenticationConverter;
        return userTokenConverter;
    }

}
