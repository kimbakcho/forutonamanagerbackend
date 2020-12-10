package com.wing.backend.forutonamanager.manager.MaliciousReply.Service.JudgementService;

import com.wing.backend.forutonamanager.forutona.FBallReply.Service.FBallReplyService;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Domain.MaliciousReply;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyResDto;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Repository.MaliciousReplyDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;

import java.time.LocalDateTime;

public class MaliciousReplyFalseReportService  implements MaliciousJudgementService<MaliciousReplyResDto> {

    final MUserInfoDataRepository mUserInfoDataRepository;
    final CustomOAuth2User customOAuth2User;
    final Integer idx;
    final MaliciousReplyDataRepository maliciousReplyDataRepository;
    final FBallReplyService fBallReplyService;

    public MaliciousReplyFalseReportService(MUserInfoDataRepository mUserInfoDataRepository,
                                            CustomOAuth2User customOAuth2User,
                                            Integer idx,
                                            MaliciousReplyDataRepository maliciousReplyDataRepository,
                                            FBallReplyService fBallReplyService) {
        this.mUserInfoDataRepository = mUserInfoDataRepository;
        this.customOAuth2User = customOAuth2User;
        this.idx = idx;
        this.maliciousReplyDataRepository = maliciousReplyDataRepository;
        this.fBallReplyService = fBallReplyService;
    }

    private void judgementReset(MaliciousReply maliciousReply) {
        maliciousReply.setFalseReportFlag(false);
        maliciousReply.setMaliciousContentFlag(false);
    }


    @Override
    public MaliciousReplyResDto judgement() {
        MaliciousReply maliciousReply = maliciousReplyDataRepository.findById(this.idx).get();
        judgementReset(maliciousReply);
        maliciousReply.setFalseReportFlag(true);

        MUserInfo mUserInfo = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();
        maliciousReply.setJudgmentUid(mUserInfo);

        maliciousReply.setJudgmentTime(LocalDateTime.now());

        return MaliciousReplyResDto.fromMaliciousReply(maliciousReply);
    }
}
