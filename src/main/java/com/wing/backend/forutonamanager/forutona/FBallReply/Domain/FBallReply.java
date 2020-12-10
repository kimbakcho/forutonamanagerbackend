package com.wing.backend.forutonamanager.forutona.FBallReply.Domain;

import com.wing.backend.forutonamanager.forutona.FBall.Domain.FBall;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FBallReply {

  @Id
  private String replyUuid;

  @JoinColumn(name = "ballUuid")
  @ManyToOne
  private FBall ballUuid;

  @JoinColumn(name = "uid")
  @ManyToOne
  private FUserInfo uid;

  private Integer replyNumber;
  private Integer replySort;
  private Integer replyDepth;
  private String replyText;
  private LocalDateTime replyUploadDateTime;
  private LocalDateTime replyUpdateDateTime;
  private Boolean deleteFlag;

  private Boolean maliciousFlag;


  public void setMaliciousFlag(Boolean maliciousFlag) {
    this.maliciousFlag = maliciousFlag;
  }
}
