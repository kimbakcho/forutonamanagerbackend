package com.wing.backend.forutonamanager.forutona.FBall.Dto;

import com.querydsl.core.annotations.QueryProjection;
import com.wing.backend.forutonamanager.forutona.FBall.Domain.FBall;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class FBallSimpleResDto {

    @QueryProjection
    public FBallSimpleResDto(String ballUuid, FUserInfo uid,
                             String ballName, LocalDateTime makeTime,
                             Long ballHits) {
        this.ballUuid = ballUuid;
        this.uid = FUserInfoResDto.fromFUserInfo(uid);
        this.ballName = ballName;
        this.makeTime = makeTime;
        this.ballHits = ballHits;
    }

    private String ballUuid;
    private FUserInfoResDto uid;
    private String ballName;
    private LocalDateTime makeTime;
    private Long ballHits;
}
