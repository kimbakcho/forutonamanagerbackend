package com.management.forutona.AuthDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

public class UserinfoAdapter extends User {
    private Userinfo userinfo;

    public UserinfoAdapter(Userinfo account) {
        super(account.getUid(), account.getPassword(), authorities(account.getRole()));
        this.userinfo = account;
    }

    private static Collection<? extends GrantedAuthority> authorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public Userinfo getAccount() {
        return userinfo;
    }
}
