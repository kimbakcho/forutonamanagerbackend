package com.wing.backend.forutonamanager.manager.MaliciousReply.Service.JudgementService;

import com.wing.backend.forutonamanager.forutona.FBallReply.Service.FBallReplyService;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Service.FUserInfoService;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Malicious.Domain.MaliciousJudgementType;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyResDto;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Repository.MaliciousReplyDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaliciousReplyJudgementServiceFactory {

    final FUserInfoService fUserInfoService;

    final MUserInfoDataRepository mUserInfoDataRepository;

    final MaliciousReplyDataRepository maliciousReplyDataRepository;

    final FBallReplyService fBallReplyService;

    public MaliciousJudgementService<MaliciousReplyResDto> getInstance(MaliciousJudgementType type,
                                                                       Integer idx,
                                                                       CustomOAuth2User customOAuth2User) throws Exception {
        switch (type) {
            case MaliciousContent:
                return new MaliciousReplyContentService(mUserInfoDataRepository,
                        customOAuth2User,
                        fUserInfoService,
                        idx,
                        maliciousReplyDataRepository, fBallReplyService);
            case FalseReport:
                return new MaliciousReplyFalseReportService(mUserInfoDataRepository,
                        customOAuth2User,
                        idx,
                        maliciousReplyDataRepository,
                        fBallReplyService);
            default:
                throw new Exception("don't Support");
        }
    }
}
