package com.wing.backend.forutonamanager.manager.MaliciousProfile.Controller;

import com.wing.backend.forutonamanager.manager.Malicious.Domain.MaliciousJudgementType;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousSearchType;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfilePageItemResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfileResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.JudgementService.MaliciousProfileJudgementServiceFactory;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.MaliciousProfileService;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.SearchService.MaliciousProfileSearchService;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.SearchService.MaliciousProfileSearchServiceFactory;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maliciousProfile")
public class MaliciousProfileController {

    final MaliciousProfileSearchServiceFactory maliciousProfileSearchServiceFactory;

    final MaliciousProfileService maliciousProfileService;

    final MaliciousProfileJudgementServiceFactory maliciousProfileJudgementServiceFactory;

    @GetMapping
    public Page<MaliciousProfilePageItemResDto> getPage(MaliciousSearchType searchType, Pageable pageable) throws Exception {
        MaliciousProfileSearchService instance = maliciousProfileSearchServiceFactory.getInstance(searchType);
        return instance.search(pageable);
    }

    @GetMapping("/idx")
    public MaliciousProfileResDto getMaliciousProfile(Integer idx){
        return maliciousProfileService.getMaliciousProfile(idx);
    }

    @PutMapping()
    @Transactional
    public MaliciousProfileResDto updateJudgement(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                               Integer idx, MaliciousJudgementType maliciousJudgementType)
            throws Exception {
        MaliciousJudgementService<MaliciousProfileResDto> instance = maliciousProfileJudgementServiceFactory.getInstance(
                maliciousJudgementType, idx,customOAuth2User);
        return instance.judgement();
    }

}
