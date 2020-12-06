package com.wing.backend.forutonamanager.manager.MaliciousProfile.Controller;

import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousSearchType;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfilePageItemResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfileResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.MaliciousProfileService;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.SearchService.MaliciousProfileSearchService;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.SearchService.MaliciousProfileSearchServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maliciousProfile")
public class MaliciousProfileController {

    final MaliciousProfileSearchServiceFactory maliciousProfileSearchServiceFactory;

    final MaliciousProfileService maliciousProfileService;

    @GetMapping
    public Page<MaliciousProfilePageItemResDto> getPage(MaliciousSearchType searchType, Pageable pageable) throws Exception {
        MaliciousProfileSearchService instance = maliciousProfileSearchServiceFactory.getInstance(searchType);
        return instance.search(pageable);
    }

    @GetMapping("/idx")
    public MaliciousProfileResDto getMaliciousProfile(Integer idx){
        return maliciousProfileService.getMaliciousProfile(idx);
    }

}
