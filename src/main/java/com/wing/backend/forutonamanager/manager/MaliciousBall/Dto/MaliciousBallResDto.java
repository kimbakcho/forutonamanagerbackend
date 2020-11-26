package com.wing.backend.forutonamanager.manager.MaliciousBall.Dto;

import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaliciousBallResDto {
     Integer idx;
     String ballUuid;
     String ballName;
     String makerNickName;
     Integer totalNumberReports;
     Integer crime;
     Integer abuse;
     Integer privacy;
     Integer sexual;
     Integer advertising;
     Integer etc;
     Integer maliciousContentFlag;
     Integer falseReportFlag;
     LocalDateTime judgmentTime;
     MUserInfoResDto judgmentUid;

     public static MaliciousBallResDto fromMaliciousBall(MaliciousBall maliciousBall) {
          MaliciousBallResDto resDto = new MaliciousBallResDto();
          resDto.idx = maliciousBall.getIdx();
          resDto.ballUuid = maliciousBall.getBallUuid();
          resDto.totalNumberReports = maliciousBall.getTotalNumberReports();
          resDto.crime = maliciousBall.getCrime();
          resDto.abuse = maliciousBall.getAbuse();
          resDto.privacy = maliciousBall.getPrivacy();
          resDto.sexual = maliciousBall.getSexual();
          resDto.advertising = maliciousBall.getAdvertising();
          resDto.etc = maliciousBall.getEtc();
          resDto.maliciousContentFlag = maliciousBall.getMaliciousContentFlag();
          resDto.falseReportFlag = maliciousBall.getFalseReportFlag();
          resDto.judgmentTime = maliciousBall.getJudgmentTime();
          if(maliciousBall.getJudgmentUid() != null){
               resDto.judgmentUid = MUserInfoResDto.fromUserInfoResDto(maliciousBall.getJudgmentUid());
          }

          return resDto;
     }
}
