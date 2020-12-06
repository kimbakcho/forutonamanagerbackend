package com.wing.backend.forutonamanager.manager.MaliciousProfile.Service.SearchService;

import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousSearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaliciousProfileSearchServiceFactory {

    final MaliciousProfileBeforeJudgmentSearchService maliciousProfileBeforeJudgmentSearchService;

    final MaliciousProfileFalseReportSearchService maliciousProfileFalseReportSearchService;

    final MaliciousProfileMaliciousContentSearchService maliciousProfileMaliciousContentSearchService;

    public MaliciousProfileSearchService getInstance(MaliciousSearchType maliciousSearchType) throws Exception {

        switch (maliciousSearchType){
            case BEFORE_JUDGMENT:
                return maliciousProfileBeforeJudgmentSearchService;
            case MALICIOUS_CONTENT:
                return maliciousProfileMaliciousContentSearchService;
            case FALSE_REPORT:
                return maliciousProfileFalseReportSearchService;
            default:
                throw new Exception("don't Support SearchType");
        }
    }
}
