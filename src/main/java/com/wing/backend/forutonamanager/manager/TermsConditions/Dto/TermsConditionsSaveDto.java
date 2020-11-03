package com.wing.backend.forutonamanager.manager.TermsConditions.Dto;

import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TermsConditionsSaveDto {
    String termsName;
    String termsContent;
}
