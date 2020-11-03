package com.wing.backend.forutonamanager.SecurityTestUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WithMockCustomUserSecurityContextFactory
        implements WithSecurityContextFactory<WithMockCustomUser> {

    final MUserInfoDataRepository mUserInfoDataRepository;

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser withMockCustomUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("normal"));
//        authorities.add(new SimpleGrantedAuthority("forutona"));
        MUserInfo mUserInfo = mUserInfoDataRepository.findById(withMockCustomUser.uid()).get();
        MUserInfoResDto mUserInfoResDto = MUserInfoResDto.fromUserInfoResDto(mUserInfo);

        ObjectMapper objectMapper = new ObjectMapper();
        Map attributes = objectMapper.convertValue(mUserInfoResDto, Map.class);

        attributes.put("forutona","forutona");

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(authorities,attributes,"forutona",mUserInfoResDto);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(customOAuth2User, "password", customOAuth2User.getAuthorities());
        context.setAuthentication(auth);

        return context;
    }
}
