package com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Service;

import com.wing.backend.forutonamanager.forutona.FBall.Domain.FBall;
import com.wing.backend.forutonamanager.forutona.FBall.Repository.FBallDataRepository;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Repository.FUserInfoDataRepository;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.MaliciousType;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.UserInfoMaliciousHistory;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Dto.UserInfoMaliciousHistoryResDto;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Repository.UserInfoMaliciousHistoryDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "forutonaAppTransactionManager")
public class UserInfoMaliciousHistoryService {

    final UserInfoMaliciousHistoryDataRepository userInfoMaliciousHistoryDataRepository;

    final FUserInfoDataRepository fUserInfoDataRepository;

    public UserInfoMaliciousHistoryResDto save(String maliciousUserUid, MaliciousType maliciousType){

        FUserInfo maliciousUser = fUserInfoDataRepository.findById(maliciousUserUid).get();

        UserInfoMaliciousHistory maliciousHistory = UserInfoMaliciousHistory.builder()
                .uid(maliciousUser)
                .maliciousDateTime(LocalDateTime.now())
                .maliciousType(maliciousType)
                .build();

        UserInfoMaliciousHistory saveItem =
                this.userInfoMaliciousHistoryDataRepository.save(maliciousHistory);

        return UserInfoMaliciousHistoryResDto.fromUserInfoMaliciousHistory(saveItem);
    }

    public Long getWhileMaliciousCount(int days,MaliciousType maliciousType){
        return this.userInfoMaliciousHistoryDataRepository
                .countByMaliciousDateTimeAfterAndMaliciousType(LocalDateTime.now().minusDays(days),maliciousType);
    }
}
