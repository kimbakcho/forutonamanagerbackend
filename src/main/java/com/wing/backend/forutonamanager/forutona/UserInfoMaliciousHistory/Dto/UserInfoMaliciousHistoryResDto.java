package com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Dto;

import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.UserInfoMaliciousHistory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoMaliciousHistoryResDto {
    Integer idx;
    FUserInfoResDto uid;
    LocalDateTime maliciousDateTime;

    public static UserInfoMaliciousHistoryResDto fromUserInfoMaliciousHistory
            (UserInfoMaliciousHistory userInfoMaliciousHistory){
        UserInfoMaliciousHistoryResDto userInfoMaliciousHistoryResDto =
                new UserInfoMaliciousHistoryResDto();

        userInfoMaliciousHistoryResDto.idx = userInfoMaliciousHistory.getIdx();

        userInfoMaliciousHistoryResDto.uid = FUserInfoResDto.fromFUserInfo(userInfoMaliciousHistory.getUid());

        userInfoMaliciousHistoryResDto.maliciousDateTime = userInfoMaliciousHistory.getMaliciousDateTime();

        return userInfoMaliciousHistoryResDto;
    }
}
