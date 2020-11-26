package com.wing.backend.forutonamanager.manager.MaliciousBall.Controller;

import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBallSearchType;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Service.SearchService.MaliciousBallSearchServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maliciousBall")
public class MaliciousBallController {

    final MaliciousBallSearchServiceFactory maliciousBallSearchServiceFactory;

    @GetMapping
    public Page<MaliciousBallResDto> getPage(MaliciousBallSearchType searchType, Pageable pageable) throws Exception {
        return maliciousBallSearchServiceFactory.getInstance(searchType).search(pageable);
    }
    
}
