package com.wing.backend.forutonamanager.manager.MaliciousReply.Controller;

import com.wing.backend.forutonamanager.manager.Malicious.Domain.MaliciousJudgementType;
import com.wing.backend.forutonamanager.manager.Malicious.Service.JudgementService.MaliciousJudgementService;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousSearchType;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfileResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.JudgementService.MaliciousProfileJudgementServiceFactory;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.SearchService.MaliciousProfileSearchService;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyPageItemResDto;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyResDto;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Service.JudgementService.MaliciousReplyJudgementServiceFactory;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Service.MaliciousReplyService;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Service.SearchService.MaliciousReplySearchService;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Service.SearchService.MaliciousReplySearchServiceFactory;
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
@RequestMapping("/maliciousReply")
public class MaliciousReplyController {

    final MaliciousReplySearchServiceFactory maliciousReplySearchServiceFactory;

    final MaliciousReplyService maliciousReplyService;

    final MaliciousReplyJudgementServiceFactory maliciousReplyJudgementServiceFactory;

    @GetMapping
    public Page<MaliciousReplyPageItemResDto> getPage(MaliciousSearchType searchType, Pageable pageable) throws Exception {
        MaliciousReplySearchService instance = maliciousReplySearchServiceFactory.getInstance(searchType);
        return instance.search(pageable);
    }

    @GetMapping("/idx")
    public MaliciousReplyResDto getItem(Integer idx){
        return maliciousReplyService.getMaliciousReply(idx);
    }

    @PutMapping()
    @Transactional
    public MaliciousReplyResDto updateJudgement(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,
                                                  Integer idx, MaliciousJudgementType maliciousJudgementType)
            throws Exception {
        MaliciousJudgementService<MaliciousReplyResDto> instance = maliciousReplyJudgementServiceFactory
                .getInstance(maliciousJudgementType, idx,customOAuth2User);
        return instance.judgement();
    }

}
