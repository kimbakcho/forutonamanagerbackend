package com.wing.backend.forutonamanager.forutona.FBall.Domain;

import com.vividsolutions.jts.geom.Point;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FBall {

  @Id
  @Column(unique = true)
  private String ballUuid;

  @JoinColumn(name = "uid")
  @ManyToOne
  FUserInfo uid;

  private Double longitude;
  private Double latitude;
  @Column(columnDefinition = "geometry(Point,4326)")
  private Point placePoint;
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

  public void setMaliciousBall(Boolean maliciousBall) {
    this.maliciousBall = maliciousBall;
  }
}
