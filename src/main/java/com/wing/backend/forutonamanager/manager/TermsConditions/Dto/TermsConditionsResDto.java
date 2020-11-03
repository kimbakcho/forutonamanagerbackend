package com.wing.backend.forutonamanager.manager.TermsConditions.Dto;

import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Domain.TermsConditions;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TermsConditionsResDto {
    Integer idx;
    String termsName;
    String termsContent;
    LocalDateTime modifyDate;
    MUserInfoResDto modifyUser;

    public static TermsConditionsResDto fromTermsConditions(TermsConditions termsConditions){
        TermsConditionsResDto termsConditionsDto = new TermsConditionsResDto();
        termsConditionsDto.idx = termsConditions.getIdx();
        termsConditionsDto.termsName = termsConditions.getTermsName();
        termsConditionsDto.termsContent = termsConditions.getTermsContent();
        termsConditionsDto.modifyDate = termsConditions.getModifyDate();
        termsConditionsDto.modifyUser = MUserInfoResDto.fromUserInfoResDto(termsConditions.getModifyUser());
        return termsConditionsDto;
    }

}
