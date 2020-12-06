package com.wing.backend.forutonamanager.forutona.FUserInfo.Controller;

import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Service.FUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fUserInfo")
public class FUserInfoController {

    final FUserInfoService fUserInfoService;

    @GetMapping("/uid")
    public FUserInfoResDto getFUserInfoResDto(String uid){
        return fUserInfoService.getFUserInfo(uid);
    }

}
