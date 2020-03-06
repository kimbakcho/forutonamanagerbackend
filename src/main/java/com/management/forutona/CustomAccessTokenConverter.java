package com.management.forutona;

import com.management.forutona.AuthDto.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Autowired
    UserService accountService;

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
        //UserAuthenticationConverter 가 나의 UserdetailsService 객체를 사용 하도록 변경한다.
        DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
        defaultUserAuthenticationConverter.setUserDetailsService(accountService);
        UserAuthenticationConverter userTokenConverter = defaultUserAuthenticationConverter;
        return userTokenConverter;
    }

}

