package com.wing.backend.forutonamanager.manager.Security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;


import java.util.Collection;


public class ForutonaCustomToken extends AbstractAuthenticationToken {

    UserInfoAdapter userInfoAdapter;

    public ForutonaCustomToken(UserInfoAdapter userInfoAdapter) {
        super(userInfoAdapter.getAuthorities());
        this.userInfoAdapter = userInfoAdapter;
    }

    @Override
    public Object getCredentials() {
        return "jwt";
    }

    @Override
    public Object getPrincipal() {
        return userInfoAdapter;
    }
}
