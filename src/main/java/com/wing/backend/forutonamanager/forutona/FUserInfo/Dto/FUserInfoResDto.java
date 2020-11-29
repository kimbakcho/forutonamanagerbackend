package com.wing.backend.forutonamanager.forutona.FUserInfo.Dto;

import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class FUserInfoResDto {

    private String uid;
    private String nickName;
    private String profilePictureUrl;
    private long gender;
    private LocalDateTime ageDate;
    private String email;
    private long forutonaAgree;
    private long forutonaManagementAgree;
    private long privateAgree;
    private long positionAgree;
    private long martketingAgree;
    private long ageLimitAgree;
    private String snsService;
    private String phoneNumber;
    private String isoCode;
    private double latitude;
    private double longitude;
    private LocalDateTime positionUpdateTime;
    private double userLevel;
    private double expPoint;
    private String fCMtoken;
    private LocalDateTime joinTime;
    private long followCount;
    private long backOut;
    private LocalDateTime lastBackOutTime;
    private String selfIntroduction;
    private double cumulativeInfluence;
    private double uPoint;
    private double naPoint;
    private long historyOpenAll;
    private long historyOpenFollowSponsor;
    private long historyOpenNoOpen;
    private long sponsorHistoryOpenAll;
    private long sponsorHistoryOpenSponAndFollowFromMe;
    private long sponsorHistoryOpenSponNoOpen;
    private long alarmChatMessage;
    private long alarmContentReply;
    private long alarmReplyAndReply;
    private long alarmFollowNewContent;
    private long alarmSponNewContent;
    private long deactivation;
    private double playerPower;
    private long maliciousUserLevel;
    private LocalDateTime stopPeriod;

    public static FUserInfoResDto fromFUserInfo(FUserInfo fUserInfo) {
        FUserInfoResDto fUserInfoResDto = new FUserInfoResDto();
        fUserInfoResDto.uid = fUserInfo.getUid();
        fUserInfoResDto.nickName = fUserInfo.getNickName();
        fUserInfoResDto.profilePictureUrl = fUserInfo.getProfilePictureUrl();
        fUserInfoResDto.gender = fUserInfo.getGender();
        fUserInfoResDto.ageDate = fUserInfo.getAgeDate();
        fUserInfoResDto.email = fUserInfo.getEmail();
        fUserInfoResDto.forutonaAgree = fUserInfo.getForutonaAgree();
        fUserInfoResDto.forutonaManagementAgree = fUserInfo.getForutonaManagementAgree();
        fUserInfoResDto.privateAgree = fUserInfo.getPrivateAgree();
        fUserInfoResDto.positionAgree = fUserInfo.getPositionAgree();
        fUserInfoResDto.martketingAgree = fUserInfo.getMartketingAgree();
        fUserInfoResDto.ageLimitAgree = fUserInfo.getAgeLimitAgree();
        fUserInfoResDto.snsService = fUserInfo.getSnsService();
        fUserInfoResDto.phoneNumber = fUserInfo.getPhoneNumber();
        fUserInfoResDto.isoCode = fUserInfo.getIsoCode();
        fUserInfoResDto.latitude = fUserInfo.getLatitude();
        fUserInfoResDto.longitude = fUserInfo.getLongitude();
        fUserInfoResDto.positionUpdateTime = fUserInfo.getPositionUpdateTime();
        fUserInfoResDto.userLevel = fUserInfo.getUserLevel();
        fUserInfoResDto.expPoint = fUserInfo.getExpPoint();
        fUserInfoResDto.fCMtoken = fUserInfo.getFCMtoken();
        fUserInfoResDto.joinTime = fUserInfo.getJoinTime();
        fUserInfoResDto.followCount = fUserInfo.getFollowCount();
        fUserInfoResDto.backOut = fUserInfo.getBackOut();
        fUserInfoResDto.lastBackOutTime = fUserInfo.getLastBackOutTime();
        fUserInfoResDto.selfIntroduction = fUserInfo.getSelfIntroduction();
        fUserInfoResDto.cumulativeInfluence = fUserInfo.getCumulativeInfluence();
        fUserInfoResDto.uPoint = fUserInfo.getUPoint();
        fUserInfoResDto.naPoint = fUserInfo.getNaPoint();
        fUserInfoResDto.historyOpenAll = fUserInfo.getHistoryOpenAll();
        fUserInfoResDto.historyOpenFollowSponsor = fUserInfo.getHistoryOpenFollowSponsor();
        fUserInfoResDto.historyOpenNoOpen = fUserInfo.getHistoryOpenNoOpen();
        fUserInfoResDto.sponsorHistoryOpenAll = fUserInfo.getSponsorHistoryOpenAll();
        fUserInfoResDto.sponsorHistoryOpenSponAndFollowFromMe = fUserInfo.getSponsorHistoryOpenSponAndFollowFromMe();
        fUserInfoResDto.sponsorHistoryOpenSponNoOpen = fUserInfo.getSponsorHistoryOpenSponNoOpen();
        fUserInfoResDto.alarmChatMessage = fUserInfo.getAlarmChatMessage();
        fUserInfoResDto.alarmContentReply = fUserInfo.getAlarmContentReply();
        fUserInfoResDto.alarmReplyAndReply = fUserInfo.getAlarmReplyAndReply();
        fUserInfoResDto.alarmFollowNewContent = fUserInfo.getAlarmFollowNewContent();
        fUserInfoResDto.alarmSponNewContent = fUserInfo.getAlarmSponNewContent();
        fUserInfoResDto.deactivation = fUserInfo.getDeactivation();
        fUserInfoResDto.playerPower = fUserInfo.getPlayerPower();
        fUserInfoResDto.maliciousUserLevel = fUserInfo.getMaliciousUserLevel();
        fUserInfoResDto.stopPeriod = fUserInfo.getStopPeriod();
        return fUserInfoResDto;
    }
}
