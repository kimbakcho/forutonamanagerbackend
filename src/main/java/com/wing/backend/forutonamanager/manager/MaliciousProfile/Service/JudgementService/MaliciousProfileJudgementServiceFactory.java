package com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.JudgementService;

import com.wing.backend.forutonamanager.forutona.FUserInfo.Service.FUserInfoService;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Repository.UserInfoMaliciousHistoryDataRepository;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Service.UserInfoMaliciousHistoryService;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Malicious.Domain.MaliciousJudgementType;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfileResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Repository.MaliciousProfileDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaliciousProfileJudgementServiceFactory {

    final MUserInfoDataRepository mUserInfoDataRepository;
    final FUserInfoService fUserInfoService;
    final MaliciousProfileDataRepository maliciousProfileDataRepository;
    final UserInfoMaliciousHistoryService userInfoMaliciousHistoryService;
    public MaliciousJudgementService<MaliciousProfileResDto> getInstance(MaliciousJudgementType type, Integer idx, CustomOAuth2User customOAuth2User) throws Exception {
        switch (type){
            case MaliciousContent:
                return new MaliciousProfileContentService(idx,mUserInfoDataRepository,
                        customOAuth2User,
                        fUserInfoService,
                        maliciousProfileDataRepository);
            case FalseReport:
                return new MaliciousProfileFalseReportService(mUserInfoDataRepository,
                        customOAuth2User,
                        userInfoMaliciousHistoryService,
                        idx,
                        maliciousProfileDataRepository);
            default:
                throw new Exception("don't Support");
        }
    }
}
