package com.wing.backend.forutonamanager.manager.Security;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;

public class UserInfoAdapter extends User {

    private MUserInfo userInfo;

    public UserInfoAdapter(MUserInfo account) {
        super(account.getUid(), "jwt", authorities(account.getHasRole()));
        this.userInfo = account;
    }
    private static Collection<? extends GrantedAuthority> authorities(String role1) {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role1));
    }
    public MUserInfo getAccount() {
        return userInfo;
    }

}
