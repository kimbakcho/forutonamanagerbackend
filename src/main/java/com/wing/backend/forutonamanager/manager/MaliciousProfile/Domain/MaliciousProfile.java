package com.wing.backend.forutonamanager.manager.MaliciousProfile.Domain;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MaliciousProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;
    String uid;
    Integer crime;
    Integer abuse;
    Integer privacy;
    Integer sexual;
    Integer advertising;
    Integer etc;
    Boolean maliciousContentFlag;
    Boolean falseReportFlag;
    LocalDateTime judgmentTime;

    @JoinColumn(name = "judgmentUid")
    @ManyToOne
    MUserInfo judgmentUid;

    Integer totalNumberReports;

    public void setMaliciousContentFlag(Boolean maliciousContentFlag) {
        this.maliciousContentFlag = maliciousContentFlag;
    }

    public void setFalseReportFlag(Boolean falseReportFlag) {
        this.falseReportFlag = falseReportFlag;
    }

    public void setJudgmentTime(LocalDateTime judgmentTime) {
        this.judgmentTime = judgmentTime;
    }

    public void setJudgmentUid(MUserInfo judgmentUid) {
        this.judgmentUid = judgmentUid;
    }
}
