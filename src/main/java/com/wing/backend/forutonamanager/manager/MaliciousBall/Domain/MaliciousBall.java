package com.wing.backend.forutonamanager.manager.MaliciousBall.Domain;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class MaliciousBall {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idx;
  private String ballUuid;
  private Integer totalNumberReports;
  private Integer crime;
  private Integer abuse;
  private Integer privacy;
  private Integer sexual;
  private Integer advertising;
  private Integer etc;
  private Integer maliciousContentFlag;
  private Integer falseReportFlag;

  private LocalDateTime judgmentTime;

  @JoinColumn(name = "judgmentUid")
  @ManyToOne(fetch = FetchType.LAZY)
  private MUserInfo judgmentUid;

  public void setMaliciousContentFlag(Integer maliciousContentFlag) {
    this.maliciousContentFlag = maliciousContentFlag;
  }

  public void setFalseReportFlag(Integer falseReportFlag) {
    this.falseReportFlag = falseReportFlag;
  }

  public void setJudgmentTime(LocalDateTime judgmentTime) {
    this.judgmentTime = judgmentTime;
  }

  public void setJudgmentUid(MUserInfo judgmentUid) {
    this.judgmentUid = judgmentUid;
  }
}
