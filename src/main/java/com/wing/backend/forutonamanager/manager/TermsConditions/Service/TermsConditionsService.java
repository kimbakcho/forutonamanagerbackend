package com.wing.backend.forutonamanager.manager.TermsConditions.Service;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import com.wing.backend.forutonamanager.manager.TermsConditions.Domain.TermsConditions;
import com.wing.backend.forutonamanager.manager.TermsConditions.Dto.TermsConditionsResDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Dto.TermsConditionsSaveDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Dto.TermsConditionsUpdateDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Repository.TermsConditionsDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TermsConditionsService {

    final TermsConditionsDataRepository termsConditionsDataRepository;

    final MUserInfoDataRepository mUserInfoDataRepository;

    public TermsConditionsResDto getTerms(Integer idx) {
        return TermsConditionsResDto.fromTermsConditions(
                termsConditionsDataRepository.findById(idx).get()
        );
    }

    public TermsConditionsResDto saveTermsConditions(CustomOAuth2User oauth2User, TermsConditionsSaveDto saveDto) {

        MUserInfo mUserInfo = mUserInfoDataRepository.findById(oauth2User.getMUserInfo().getUid()).get();

        TermsConditions termsConditions = TermsConditions.builder().termsName(saveDto.getTermsName())
                .modifyDate(LocalDateTime.now())
                .modifyUser(mUserInfo)
                .termsContent(saveDto.getTermsContent())
                .build();

        TermsConditions saveItem = termsConditionsDataRepository.save(termsConditions);

        return TermsConditionsResDto.fromTermsConditions(saveItem);
    }


    public TermsConditionsResDto updateTermsConditions(CustomOAuth2User oauth2User, TermsConditionsUpdateDto updateDto) {

        MUserInfo mUserInfo = mUserInfoDataRepository.findById(oauth2User.getMUserInfo().getUid()).get();

        TermsConditions termsConditions = termsConditionsDataRepository.findById(updateDto.getIdx()).get();

        termsConditions.setTermsName(updateDto.getTermsName());
        termsConditions.setTermsContent(updateDto.getTermsContent());
        termsConditions.setModifyUser(mUserInfo);
        termsConditions.setModifyDate(LocalDateTime.now());

        TermsConditions saveItem = termsConditionsDataRepository.save(termsConditions);

        return TermsConditionsResDto.fromTermsConditions(saveItem);
    }


    public Integer deleteTermsConditions(Integer idx) {
        termsConditionsDataRepository.deleteById(idx);
        return idx;
    }
}
