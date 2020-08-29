package com.wing.backend.forutonamanager.manager.Security;

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
        UserInfoAdapter userinfoAdapter = (UserInfoAdapter) authentication.getPrincipal();
        additionalInfo.put("uid", userinfoAdapter.getAccount().getUid());
        additionalInfo.put("groupName", userinfoAdapter.getAccount().getGroupName());
        additionalInfo.put("userName", userinfoAdapter.getAccount().getUserName());
        additionalInfo.put("role", userinfoAdapter.getAccount().getHasRole());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
