package com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto;

import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaliciousProfilePageItemResDto {
    Integer idx;
    String userUid;
    String nickName;

    LocalDateTime joinTime;
    Long followerCount;
    Integer totalNumberReports;
    Boolean falseReportFlag;
    Boolean maliciousContentFlag;
}

