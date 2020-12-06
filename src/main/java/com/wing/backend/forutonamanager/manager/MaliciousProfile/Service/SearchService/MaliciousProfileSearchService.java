package com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.SearchService;

import com.wing.backend.forutonamanager.forutona.FBall.Dto.FBallSimpleResDto;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Service.FUserInfoService;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Domain.MaliciousProfile;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfilePageItemResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class MaliciousProfileSearchService {

    final FUserInfoService fUserInfoService;

    public abstract Page<MaliciousProfilePageItemResDto> search(Pageable pageable);

    protected Page<MaliciousProfilePageItemResDto> makeMaliciousProfilePageItemResDto(Page<MaliciousProfile> maliciousProfiles){
        List<String> userCollect = maliciousProfiles.getContent()
                .stream()
                .map(x -> x.getUid())
                .collect(Collectors.toList());

        List<FUserInfoResDto> userInfoResDtos = fUserInfoService.userUidToFUserInfo(userCollect);

        Page<MaliciousProfilePageItemResDto> maliciousProfilePageItemPage = maliciousProfiles.map(x->{
            Optional<FUserInfoResDto> OpFUserInfoResDto = userInfoResDtos.stream()
                    .filter(p -> p.getUid().equals(x.getUid()))
                    .findFirst();

            MaliciousProfilePageItemResDto maliciousProfilePageItemResDto = new MaliciousProfilePageItemResDto();
            maliciousProfilePageItemResDto.setIdx(x.getIdx());
            maliciousProfilePageItemResDto.setUserUid(x.getUid());
            maliciousProfilePageItemResDto.setFalseReportFlag(x.getFalseReportFlag());
            maliciousProfilePageItemResDto.setMaliciousContentFlag(x.getMaliciousContentFlag());
            maliciousProfilePageItemResDto.setTotalNumberReports(x.getTotalNumberReports());

            if(OpFUserInfoResDto.isPresent()){
                FUserInfoResDto fUserInfoResDto = OpFUserInfoResDto.get();
                maliciousProfilePageItemResDto.setNickName(fUserInfoResDto.getNickName());
                maliciousProfilePageItemResDto.setJoinTime(fUserInfoResDto.getJoinTime());
                maliciousProfilePageItemResDto.setFollowerCount(fUserInfoResDto.getFollowCount());
            }else {
                maliciousProfilePageItemResDto.setNickName("don't know");
                maliciousProfilePageItemResDto.setFollowerCount(0L);
            }
            return maliciousProfilePageItemResDto;
        });

        return maliciousProfilePageItemPage;
    }
}
