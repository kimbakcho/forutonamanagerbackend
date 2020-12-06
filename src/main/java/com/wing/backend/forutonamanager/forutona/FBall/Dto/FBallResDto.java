package com.wing.backend.forutonamanager.forutona.FBall.Dto;

import com.wing.backend.forutonamanager.forutona.FBall.Domain.FBall;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FBallResDto {

    private String ballUuid;
    private FUserInfoResDto uid;
    private Double longitude;
    private Double latitude;
    private String ballName;
    private String ballType;
    private LocalDateTime makeTime;
    private String ballState;
    private String placeAddress;
    private String administrativeArea;
    private String country;
    private Double pointReward;
    private Double influenceReward;
    private LocalDateTime activationTime;
    private String ballPassword;
    private Double hasPassword;
    private Long ballHits;
    private Double ballLikes;
    private Double ballDisLikes;
    private Long ballPower;
    private Long joinPlayer;
    private Long maximumPlayers;
    private Double starPoints;
    private Long expGiveFlag;
    private Double makeExp;
    private Long commentCount;
    private Double userExp;
    private String description;
    private Long contributor;
    private Long ballDeleteFlag;
    private Boolean maliciousBall;

    public static FBallResDto fromFBall(FBall fBall){
        FBallResDto fBallResDto = new FBallResDto();
        fBallResDto.ballUuid = fBall.getBallUuid();
        fBallResDto.uid = FUserInfoResDto.fromFUserInfo(fBall.getUid());
        fBallResDto.longitude = fBall.getLongitude();
        fBallResDto.latitude = fBall.getLatitude();
        fBallResDto.ballName = fBall.getBallName();
        fBallResDto.ballType = fBall.getBallType();
        fBallResDto.makeTime = fBall.getMakeTime();
        fBallResDto.ballState = fBall.getBallState();
        fBallResDto.placeAddress = fBall.getPlaceAddress();
        fBallResDto.administrativeArea = fBall.getAdministrativeArea();
        fBallResDto.country = fBall.getCountry();
        fBallResDto.pointReward = fBall.getPointReward();
        fBallResDto.influenceReward = fBall.getInfluenceReward();
        fBallResDto.activationTime = fBall.getActivationTime();
        fBallResDto.ballPassword = fBall.getBallPassword();
        fBallResDto.hasPassword = fBall.getHasPassword();
        fBallResDto.ballHits = fBall.getBallHits();
        fBallResDto.ballLikes = fBall.getBallLikes();
        fBallResDto.ballDisLikes = fBall.getBallDisLikes();
        fBallResDto.ballPower = fBall.getBallPower();
        fBallResDto.joinPlayer = fBall.getJoinPlayer();
        fBallResDto.maximumPlayers = fBall.getMaximumPlayers();
        fBallResDto.starPoints = fBall.getStarPoints();
        fBallResDto.expGiveFlag = fBall.getExpGiveFlag();
        fBallResDto.makeExp = fBall.getMakeExp();
        fBallResDto.commentCount = fBall.getCommentCount();
        fBallResDto.userExp = fBall.getUserExp();
        fBallResDto.description = fBall.getDescription();
        fBallResDto.contributor = fBall.getContributor();
        fBallResDto.ballDeleteFlag = fBall.getBallDeleteFlag();
        fBallResDto.maliciousBall = fBall.getMaliciousBall();
        return fBallResDto;
    }

}
