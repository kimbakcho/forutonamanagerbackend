package com.wing.backend.forutonamanager.manager.MaliciousBall.Service.SearchService;

import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBallSearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaliciousBallSearchServiceFactory {

    final MaliciousBallBeforeJudgmentSearchService maliciousBallBeforeJudgmentSearchService;

    final MaliciousBallFalseReportSearchService maliciousBallFalseReportSearchService;

    final MaliciousBallMaliciousContentSearchService maliciousBallMaliciousContentSearchService;

    public MaliciousBallSearchService getInstance(MaliciousBallSearchType maliciousBallSearchType) throws Exception {
        switch (maliciousBallSearchType) {
            case BEFORE_JUDGMENT:
                return maliciousBallBeforeJudgmentSearchService;
            case MALICIOUS_CONTENT:
                return maliciousBallMaliciousContentSearchService;
            case FALSE_REPORT:
                return maliciousBallFalseReportSearchService;
            default:
                throw new Exception("don't support search Type");
        }
    }

}
