package com.wing.backend.forutonamanager.manager.MaliciousBall.Service.JudgementService;

import com.wing.backend.forutonamanager.forutona.FBall.Dto.FBallResDto;
import com.wing.backend.forutonamanager.forutona.FBall.Service.FBallService;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.MaliciousType;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Service.UserInfoMaliciousHistoryService;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Repository.MaliciousBallDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;

import java.time.LocalDateTime;

public class MaliciousBallFalseReportService implements MaliciousJudgementService<MaliciousBallResDto> {
    final Integer idx;
    final MaliciousBallDataRepository maliciousBallDataRepository;
    final MUserInfoDataRepository mUserInfoDataRepository;

    final UserInfoMaliciousHistoryService userInfoMaliciousHistoryService;

    final FBallService fBallService;

    final CustomOAuth2User customOAuth2User;
    public MaliciousBallFalseReportService(Integer idx, MaliciousBallDataRepository maliciousBallDataRepository,
                                           MUserInfoDataRepository mUserInfoDataRepository,
                                           UserInfoMaliciousHistoryService userInfoMaliciousHistoryService,
                                           FBallService fBallService, CustomOAuth2User customOAuth2User) {
        this.idx = idx;
        this.maliciousBallDataRepository = maliciousBallDataRepository;
        this.mUserInfoDataRepository = mUserInfoDataRepository;
        this.userInfoMaliciousHistoryService = userInfoMaliciousHistoryService;
        this.fBallService = fBallService;
        this.customOAuth2User = customOAuth2User;
    }

    private  void judgementReset(MaliciousBall maliciousBall){
        maliciousBall.setFalseReportFlag(false);
        maliciousBall.setMaliciousContentFlag(false);
    }

    @Override
    public MaliciousBallResDto judgement() {


        MaliciousBall maliciousBall = maliciousBallDataRepository.findById(idx).get();

        judgementReset(maliciousBall);

        maliciousBall.setFalseReportFlag(true);

        maliciousBall.setJudgmentTime(LocalDateTime.now());

        MUserInfo mUserInfo = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();
        maliciousBall.setJudgmentUid(mUserInfo);

        FBallResDto ball = fBallService.getBall(maliciousBall.getBallUuid());

        userInfoMaliciousHistoryService.save(ball.getUid().getUid(), MaliciousType.MaliciousBallFalseReport);

        return MaliciousBallResDto.fromMaliciousBall(maliciousBall);
    }
}
