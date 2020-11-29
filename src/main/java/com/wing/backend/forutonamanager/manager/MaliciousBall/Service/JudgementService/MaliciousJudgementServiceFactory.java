package com.wing.backend.forutonamanager.manager.MaliciousBall.Service.JudgementService;

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

    public MaliciousJudgementService getInstance(MaliciousJudgementType type, Integer idx, CustomOAuth2User customOAuth2User) throws Exception {
        switch (type) {
            case MaliciousContent:
                return new MaliciousContentService(idx, maliciousBallDataRepository,
                        mUserInfoDataRepository, customOAuth2User);
            case FalseReport:
                return new MaliciousFalseReportService(idx, maliciousBallDataRepository,
                        mUserInfoDataRepository, customOAuth2User);
            default:
                throw new Exception("don't Support");
        }
    }
}
