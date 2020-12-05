package com.wing.backend.forutonamanager.forutona.FUserInfo.Service;

import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.MaliciousLevel;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Repository.FUserInfoDataRepository;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.MaliciousType;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Service.UserInfoMaliciousHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.MaliciousLevel.LEVEL2;
import static com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.MaliciousLevel.LEVEL5;

@Service
@Transactional(transactionManager = "forutonaAppTransactionManager")
@RequiredArgsConstructor
public class FUserInfoService {

    final FUserInfoDataRepository fUserInfoDataRepository;

    final UserInfoMaliciousHistoryService userInfoMaliciousHistoryService;

    public Long updateMaliciousCount(String uid,MaliciousType maliciousType){
        FUserInfo fUserInfo = fUserInfoDataRepository.findById(uid).get();
        userInfoMaliciousHistoryService.save(fUserInfo.getUid(), MaliciousType.MaliciousBallContent);
        Long whileMaliciousCount = userInfoMaliciousHistoryService.getWhileMaliciousCount(365,maliciousType);
        fUserInfo.setMaliciousCount(whileMaliciousCount);
        MaliciousLevel[] values = MaliciousLevel.values();
        List<MaliciousLevel> collect = Arrays.stream(values).sorted
                ((o1, o2) -> Integer.compare(o2.value.getCount(),o1.value.getCount()))
                .collect(Collectors.toList());

        for(MaliciousLevel level: collect){
            if(whileMaliciousCount >= level.value.getCount()){
                fUserInfo.setStopPeriod(LocalDateTime.now().plusDays(level.value.getStopDay()));
                break;
            }
        }

        return whileMaliciousCount;

    }

    public FUserInfoResDto getFUserInfo(String uid){
        return FUserInfoResDto.fromFUserInfo(fUserInfoDataRepository.findById(uid).get()) ;
    }

}
