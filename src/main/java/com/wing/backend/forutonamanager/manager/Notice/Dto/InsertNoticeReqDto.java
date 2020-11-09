package com.wing.backend.forutonamanager.manager.Notice.Dto;

import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsertNoticeReqDto {
    String title;
    String content;
    Boolean openFlag;
}
