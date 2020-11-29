package com.wing.backend.forutonamanager.manager.MaliciousBall.Service.JudgementService;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Repository.MaliciousBallDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
public class MaliciousContentService implements MaliciousJudgementService{

    final Integer idx;
    final MaliciousBallDataRepository maliciousBallDataRepository;
    final MUserInfoDataRepository mUserInfoDataRepository;
    final CustomOAuth2User customOAuth2User;

    public MaliciousContentService(Integer idx,
                                   MaliciousBallDataRepository maliciousBallDataRepository,
                                   MUserInfoDataRepository mUserInfoDataRepository,
                                   CustomOAuth2User customOAuth2User) {
        this.idx = idx;
        this.maliciousBallDataRepository = maliciousBallDataRepository;
        this.mUserInfoDataRepository = mUserInfoDataRepository;
        this.customOAuth2User = customOAuth2User;
    }

    @Override
    public MaliciousBallResDto judgement() {
        MaliciousBall maliciousBall = maliciousBallDataRepository.findById(idx).get();

        maliciousBall.setMaliciousContentFlag(1);
        maliciousBall.setJudgmentTime(LocalDateTime.now());

        MUserInfo mUserInfo = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();
        maliciousBall.setJudgmentUid(mUserInfo);

        return MaliciousBallResDto.fromMaliciousBall(maliciousBall);
    }
}
