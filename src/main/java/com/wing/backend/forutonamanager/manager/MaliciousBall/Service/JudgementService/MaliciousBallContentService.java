package com.wing.backend.forutonamanager.manager.MaliciousBall.Service.JudgementService;

import com.wing.backend.forutonamanager.forutona.FBall.Dto.FBallResDto;
import com.wing.backend.forutonamanager.forutona.FBall.Service.FBallService;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Service.FUserInfoService;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.MaliciousType;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Repository.MaliciousBallDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;

import java.time.LocalDateTime;


public class MaliciousBallContentService implements MaliciousJudgementService<MaliciousBallResDto> {

    final Integer idx;
    final MaliciousBallDataRepository maliciousBallDataRepository;
    final MUserInfoDataRepository mUserInfoDataRepository;
    final CustomOAuth2User customOAuth2User;
    final FBallService fBallService;
    final FUserInfoService fUserInfoService;

    public MaliciousBallContentService(Integer idx,
                                       MaliciousBallDataRepository maliciousBallDataRepository,
                                       MUserInfoDataRepository mUserInfoDataRepository,
                                       CustomOAuth2User customOAuth2User,
                                       FBallService fBallService,
                                       FUserInfoService fUserInfoService) {
        this.idx = idx;
        this.maliciousBallDataRepository = maliciousBallDataRepository;
        this.mUserInfoDataRepository = mUserInfoDataRepository;
        this.customOAuth2User = customOAuth2User;

        this.fBallService = fBallService;
        this.fUserInfoService = fUserInfoService;
    }


    private  void judgementReset(MaliciousBall maliciousBall){
        maliciousBall.setFalseReportFlag(false);
        maliciousBall.setMaliciousContentFlag(false);
    }

    @Override
    public MaliciousBallResDto judgement() {
        MaliciousBall maliciousBall = maliciousBallDataRepository.findById(idx).get();
        judgementReset(maliciousBall);

        maliciousBall.setMaliciousContentFlag(true);
        maliciousBall.setJudgmentTime(LocalDateTime.now());

        MUserInfo mUserInfo = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();
        maliciousBall.setJudgmentUid(mUserInfo);

        FBallResDto fBallResDto = fBallService.getBall(maliciousBall.getBallUuid());

        fBallService.updateMaliciousBall(maliciousBall.getBallUuid());

        FUserInfoResDto makerUser = fBallResDto.getUid();

        fUserInfoService.updateMaliciousCount(makerUser.getUid(), MaliciousType.MaliciousBallContent);

        return MaliciousBallResDto.fromMaliciousBall(maliciousBall);
    }
}
