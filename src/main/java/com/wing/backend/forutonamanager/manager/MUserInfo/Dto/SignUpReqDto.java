package com.wing.backend.forutonamanager.manager.MUserInfo.Dto;

import lombok.Data;

@Data
public class SignUpReqDto {
    String uid;
    String passWord;
    String userName;
    String groupName;
}
