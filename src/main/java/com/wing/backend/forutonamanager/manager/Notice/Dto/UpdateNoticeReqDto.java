package com.wing.backend.forutonamanager.manager.Notice.Dto;

import lombok.Data;

@Data
public class UpdateNoticeReqDto {
    Integer idx;
    String title;
    String content;
    Boolean openFlag;
}
