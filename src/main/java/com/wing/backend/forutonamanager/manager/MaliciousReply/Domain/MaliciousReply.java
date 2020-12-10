package com.wing.backend.forutonamanager.manager.MaliciousReply.Domain;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MaliciousReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String replyUuid;

    private Integer crime;
    private Integer abuse;
    private Integer privacy;
    private Integer sexual;
    private Integer advertising;
    private Integer etc;
    private Integer totalNumberReports;
    private Boolean maliciousContentFlag;
    private Boolean falseReportFlag;
    private LocalDateTime judgmentTime;

    @JoinColumn(name = "judgmentUid")
    @ManyToOne
    private MUserInfo judgmentUid;

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
