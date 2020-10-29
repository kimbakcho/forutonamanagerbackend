package com.wing.backend.forutonamanager.manager.Security;


import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;




import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthConverter implements Converter<Jwt, ForutonaCustomToken> {

    final UserInfoDetailService userDetailsService;

    @Override
    public ForutonaCustomToken convert(Jwt jwt) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwt.getClaim("user_name"));

        return new ForutonaCustomToken((UserInfoAdapter) userDetails);
    }
}
