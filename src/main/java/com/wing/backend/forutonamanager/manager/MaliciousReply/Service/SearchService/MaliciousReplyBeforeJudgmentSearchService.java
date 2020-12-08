package com.wing.backend.forutonamanager.manager.MaliciousReply.Service.SearchService;

import com.wing.backend.forutonamanager.forutona.FBallReply.Service.FBallReplyService;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyPageItemResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class MaliciousReplyBeforeJudgmentSearchService extends MaliciousReplySearchService{

    public MaliciousReplyBeforeJudgmentSearchService(FBallReplyService fBallReplyService) {
        super(fBallReplyService);
    }

    @Override
    public Page<MaliciousReplyPageItemResDto> search(Pageable pageable) {
        return null;
    }
}
