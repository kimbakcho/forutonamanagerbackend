package com.wing.backend.forutonamanager.manager.MUserInfo.Controller;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.SignUpReqDto;
import com.wing.backend.forutonamanager.manager.MUserInfo.Service.MUserInfoService;
import com.wing.backend.forutonamanager.manager.Security.UserInfoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MUserInfoController {

    final MUserInfoService mUserInfoService;

    @PostMapping("/MUserInfo/SignUp")
    void SignUp(@RequestBody SignUpReqDto reqDto) throws Exception {
        mUserInfoService.signUp(reqDto);
    }

    @GetMapping("/MUserInfo/Me")
    MUserInfoResDto getMe(@AuthenticationPrincipal UserInfoAdapter userInfoAdapter) throws Exception {
        return mUserInfoService.signInUser(userInfoAdapter.getAccount().getUid());
    }

    @GetMapping("/test")
    String TEST() throws Exception {
        return "TEST";
    }
}
