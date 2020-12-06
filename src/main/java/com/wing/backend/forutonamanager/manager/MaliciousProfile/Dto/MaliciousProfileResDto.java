package com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Domain.MaliciousProfile;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class MaliciousProfileResDto {
    Integer idx;
    String uid;
    Integer crime;
    Integer abuse;
    Integer privacy;
    Integer sexual;
    Integer advertising;
    Integer etc;
    Boolean maliciousContentFlag;
    Boolean falseReportFlag;
    LocalDateTime judgmentTime;
    MUserInfoResDto judgmentUid;
    Integer totalNumberReports;

    public static MaliciousProfileResDto fromMaliciousProfile(MaliciousProfile maliciousProfile){
        MaliciousProfileResDto maliciousProfileResDto = new MaliciousProfileResDto();

        maliciousProfileResDto.idx = maliciousProfile.getIdx();
        maliciousProfileResDto.uid = maliciousProfile.getUid();
        maliciousProfileResDto.crime = maliciousProfile.getCrime();
        maliciousProfileResDto.abuse = maliciousProfile.getAbuse();
        maliciousProfileResDto.privacy = maliciousProfile.getPrivacy();
        maliciousProfileResDto.sexual = maliciousProfile.getSexual();
        maliciousProfileResDto.advertising = maliciousProfile.getAdvertising();
        maliciousProfileResDto.etc = maliciousProfile.getEtc();
        maliciousProfileResDto.maliciousContentFlag = maliciousProfile.getMaliciousContentFlag();
        maliciousProfileResDto.falseReportFlag = maliciousProfile.getFalseReportFlag();
        if(maliciousProfile.getJudgmentTime() != null){
            maliciousProfileResDto.judgmentTime = maliciousProfile.getJudgmentTime();
        }
        if(maliciousProfile.getJudgmentUid()!= null){
            maliciousProfileResDto.judgmentUid = MUserInfoResDto.fromUserInfoResDto(maliciousProfile.getJudgmentUid());
        }
        maliciousProfileResDto.totalNumberReports = maliciousProfile.getTotalNumberReports();
        return maliciousProfileResDto;

    }
}
