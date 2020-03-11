package com.management.forutona;

import com.management.forutona.Auth.UserinfoAdapter;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        UserinfoAdapter userinfoAdapter = (UserinfoAdapter) authentication.getPrincipal();
        additionalInfo.put("uid", userinfoAdapter.getAccount().getUid());
        additionalInfo.put("role", userinfoAdapter.getAccount().getRole());
        additionalInfo.put("nickName", userinfoAdapter.getAccount().getNickname());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
