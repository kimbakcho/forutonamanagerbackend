package com.wing.backend.forutonamanager.manager.MaliciousBall.Service.JudgementService;

import com.wing.backend.forutonamanager.forutona.FBall.Service.FBallService;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Service.FUserInfoService;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Service.UserInfoMaliciousHistoryService;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousJudgementType;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Repository.MaliciousBallDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaliciousJudgementServiceFactory {

    final MaliciousBallDataRepository maliciousBallDataRepository;

    final MUserInfoDataRepository mUserInfoDataRepository;

    final FBallService fBallService;

    final FUserInfoService fUserInfoService;

    final UserInfoMaliciousHistoryService userInfoMaliciousHistoryService;

    public MaliciousJudgementService getInstance(MaliciousJudgementType type, Integer idx, CustomOAuth2User customOAuth2User) throws Exception {
        switch (type) {
            case MaliciousContent:
                return new MaliciousContentService(idx, maliciousBallDataRepository,
                        mUserInfoDataRepository, customOAuth2User,
                        fBallService,fUserInfoService);
            case FalseReport:
                return new MaliciousFalseReportService(idx, maliciousBallDataRepository,
                        mUserInfoDataRepository, userInfoMaliciousHistoryService, fBallService, customOAuth2User);
            default:
                throw new Exception("don't Support");
        }
    }
}
