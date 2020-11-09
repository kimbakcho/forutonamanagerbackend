package com.wing.backend.forutonamanager.manager.Security;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserService extends DefaultOAuth2UserService {

    final MUserInfoDataRepository mUserInfoDataRepository;

    @Override
    public CustomOAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        MUserInfo mUserInfo = mUserInfoDataRepository.findById((String) oAuth2User.getAttributes().get("uid")).get();
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(oAuth2User.getAuthorities()
                ,oAuth2User.getAttributes(),"sub", MUserInfoResDto.fromUserInfoResDto(mUserInfo));
        return customOAuth2User;
    }
}
