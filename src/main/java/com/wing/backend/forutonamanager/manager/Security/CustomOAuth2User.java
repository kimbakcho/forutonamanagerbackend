package com.wing.backend.forutonamanager.manager.Security;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;


public class CustomOAuth2User extends DefaultOAuth2User {

    final MUserInfoResDto mUserInfoResDto;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey,MUserInfoResDto mUserInfoResDto) {
        super(authorities, attributes, nameAttributeKey);
        this.mUserInfoResDto = mUserInfoResDto;
    }

    public MUserInfoResDto getMUserInfo() {
        return mUserInfoResDto;
    }

}
