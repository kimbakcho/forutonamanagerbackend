package com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.JudgementService;

import com.wing.backend.forutonamanager.forutona.FUserInfo.Service.FUserInfoService;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.MaliciousType;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Domain.MaliciousProfile;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfileResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Repository.MaliciousProfileDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;

import java.time.LocalDateTime;

public class MaliciousProfileContentService implements MaliciousJudgementService<MaliciousProfileResDto> {

    final MUserInfoDataRepository mUserInfoDataRepository;
    final CustomOAuth2User customOAuth2User;
    final FUserInfoService fUserInfoService;
    final Integer idx;
    final MaliciousProfileDataRepository maliciousProfileDataRepository;

    public MaliciousProfileContentService(
            Integer idx,
            MUserInfoDataRepository mUserInfoDataRepository,
            CustomOAuth2User customOAuth2User,
            FUserInfoService fUserInfoService,
            MaliciousProfileDataRepository maliciousProfileDataRepository
            ) {
        this.mUserInfoDataRepository = mUserInfoDataRepository;
        this.customOAuth2User = customOAuth2User;
        this.fUserInfoService = fUserInfoService;
        this.idx = idx;
        this.maliciousProfileDataRepository = maliciousProfileDataRepository;
    }


    private void judgementReset(MaliciousProfile maliciousProfile) {
        maliciousProfile.setFalseReportFlag(false);
        maliciousProfile.setMaliciousContentFlag(false);
    }

    @Override
    public MaliciousProfileResDto judgement() {
        MaliciousProfile maliciousProfile = maliciousProfileDataRepository.findById(this.idx).get();
        judgementReset(maliciousProfile);
        maliciousProfile.setMaliciousContentFlag(true);
        maliciousProfile.setJudgmentTime(LocalDateTime.now());

        MUserInfo mUserInfo = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();
        maliciousProfile.setJudgmentUid(mUserInfo);


        fUserInfoService.updateMaliciousCount(maliciousProfile.getUid(), MaliciousType.MaliciousProfileContent);

        return MaliciousProfileResDto.fromMaliciousProfile(maliciousProfile);
    }

}
