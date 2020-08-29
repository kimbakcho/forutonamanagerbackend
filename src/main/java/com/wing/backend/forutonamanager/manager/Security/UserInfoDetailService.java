package com.wing.backend.forutonamanager.manager.Security;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoDetailService implements UserDetailsService {

    final private MUserInfoDataRepository mUserInfoDataRepository;


    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        MUserInfo userInfo = mUserInfoDataRepository.findById(uid).get();
        return new UserInfoAdapter(userInfo);
    }
}
