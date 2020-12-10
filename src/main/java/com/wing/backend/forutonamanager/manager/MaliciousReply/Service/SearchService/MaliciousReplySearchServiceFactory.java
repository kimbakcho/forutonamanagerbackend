package com.wing.backend.forutonamanager.manager.MaliciousReply.Service.SearchService;

import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousSearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaliciousReplySearchServiceFactory {

    final MaliciousReplyBeforeJudgmentSearchService maliciousReplyBeforeJudgmentSearchService;

    final MaliciousReplyFalseReportSearchService maliciousReplyFalseReportSearchService;

    final MaliciousReplyMaliciousContentSearchService maliciousReplyMaliciousContentSearchService;

    public MaliciousReplySearchService getInstance(MaliciousSearchType maliciousSearchType) throws Exception {
        switch (maliciousSearchType) {
            case BEFORE_JUDGMENT:
                return maliciousReplyBeforeJudgmentSearchService;
            case MALICIOUS_CONTENT:
                return maliciousReplyMaliciousContentSearchService;
            case FALSE_REPORT:
                return maliciousReplyFalseReportSearchService;
            default:
                throw new Exception("don't Support SearchType");
        }
    }
}
