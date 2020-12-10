package com.wing.backend.forutonamanager.manager.MaliciousReply.Service.JudgementService;

import com.wing.backend.forutonamanager.forutona.FBallReply.Dto.FBallReplyResDto;
import com.wing.backend.forutonamanager.forutona.FBallReply.Service.FBallReplyService;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Service.FUserInfoService;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.MaliciousType;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Domain.MaliciousProfile;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Domain.MaliciousReply;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyResDto;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Repository.MaliciousReplyDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;

import java.time.LocalDateTime;


public class MaliciousReplyContentService implements MaliciousJudgementService<MaliciousReplyResDto> {

    final MUserInfoDataRepository mUserInfoDataRepository;
    final CustomOAuth2User customOAuth2User;
    final FUserInfoService fUserInfoService;
    final Integer idx;
    final MaliciousReplyDataRepository maliciousReplyDataRepository;
    final FBallReplyService fBallReplyService;

    public MaliciousReplyContentService(MUserInfoDataRepository mUserInfoDataRepository,
                                        CustomOAuth2User customOAuth2User,
                                        FUserInfoService fUserInfoService,
                                        Integer idx, MaliciousReplyDataRepository maliciousReplyDataRepository,
                                        FBallReplyService fBallReplyService) {
        this.mUserInfoDataRepository = mUserInfoDataRepository;
        this.customOAuth2User = customOAuth2User;
        this.fUserInfoService = fUserInfoService;
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
        maliciousReply.setMaliciousContentFlag(true);

        MUserInfo mUserInfo = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();
        maliciousReply.setJudgmentUid(mUserInfo);

        maliciousReply.setJudgmentTime(LocalDateTime.now());

        FBallReplyResDto reply = fBallReplyService.getReply(maliciousReply.getReplyUuid());
        fBallReplyService.updateMaliciousReply(maliciousReply.getReplyUuid());

        fUserInfoService.updateMaliciousCount(reply.getUid().getUid(), MaliciousType.MaliciousProfileContent);

        return MaliciousReplyResDto.fromMaliciousReply(maliciousReply);
    }
}
