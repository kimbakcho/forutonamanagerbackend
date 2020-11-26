package com.wing.backend.forutonamanager.forutona.FUserInfo.Domain;

import com.vividsolutions.jts.geom.Point;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "UserInfo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FUserInfo {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String uid;
  private String nickName;
  private String profilePictureUrl;
  private Long gender;
  private LocalDateTime ageDate;
  private String email;
  private Long forutonaAgree;
  private Long forutonaManagementAgree;
  private Long privateAgree;
  private Long positionAgree;
  private Long martketingAgree;
  private Long ageLimitAgree;
  private String snsService;
  private String phoneNumber;
  private String isoCode;
  private Double latitude;
  private Double longitude;
  @Column(columnDefinition = "geometry(Point,4326)")
  private Point placePoint;
  private LocalDateTime positionUpdateTime;
  private Double userLevel;
  private Double expPoint;
  private String fCMtoken;
  private LocalDateTime joinTime;
  private Long followCount;
  private Long backOut;
  private LocalDateTime lastBackOutTime;
  private String selfIntroduction;
  private Double cumulativeInfluence;
  private Double uPoint;
  private Double naPoint;
  private Long historyOpenAll;
  private Long historyOpenFollowSponsor;
  private Long historyOpenNoOpen;
  private Long sponsorHistoryOpenAll;
  private Long sponsorHistoryOpenSponAndFollowFromMe;
  private Long sponsorHistoryOpenSponNoOpen;
  private Long alarmChatMessage;
  private Long alarmContentReply;
  private Long alarmReplyAndReply;
  private Long alarmFollowNewContent;
  private Long alarmSponNewContent;
  private Long deactivation;
  private Double playerPower;
  private Long maliciousUserLevel;
  private LocalDateTime stopPeriod;

}
