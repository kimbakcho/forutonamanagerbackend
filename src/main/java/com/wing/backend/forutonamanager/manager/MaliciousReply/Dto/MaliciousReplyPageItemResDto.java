package com.wing.backend.forutonamanager.manager.MaliciousReply.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaliciousReplyPageItemResDto {
    Integer idx;
    String replyUuid;
    String replyText;
    String replyUserNickName;
    LocalDateTime replyUploadTime;
    Integer totalNumberReports;
    Boolean falseReportFlag;
    Boolean maliciousContentFlag;

}
