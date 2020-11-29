package com.wing.backend.forutonamanager.manager.MaliciousBall.Controller;

import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBallSearchType;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousJudgementType;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Repository.MaliciousBallDataRepository;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Service.JudgementService.MaliciousJudgementServiceFactory;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Service.SearchService.MaliciousBallSearchServiceFactory;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maliciousBall")
public class MaliciousBallController {

    final MaliciousBallSearchServiceFactory maliciousBallSearchServiceFactory;

    final MaliciousJudgementServiceFactory maliciousJudgementServiceFactory;

    final MaliciousBallDataRepository maliciousBallDataRepository;

    @GetMapping
    public Page<MaliciousBallResDto> getPage(MaliciousBallSearchType searchType, Pageable pageable)
            throws Exception {
        return maliciousBallSearchServiceFactory.getInstance(searchType).search(pageable);
    }

    @GetMapping("/idx")
    public MaliciousBallResDto getMaliciousBall(Integer idx) throws Exception {
        MaliciousBall maliciousBall = maliciousBallDataRepository.findById(idx).get();
        return MaliciousBallResDto.fromMaliciousBall(maliciousBall);
    }

    @PutMapping()
    public MaliciousBallResDto updateJudgement(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                               Integer idx, MaliciousJudgementType maliciousJudgementType)
            throws Exception {
        MaliciousJudgementService instance = maliciousJudgementServiceFactory.getInstance(
                maliciousJudgementType, idx,customOAuth2User);
        return instance.judgement();
    }
}
