package com.wing.backend.forutonamanager.manager.MaliciousReply.Service.SearchService;

import com.wing.backend.forutonamanager.forutona.FBallReply.Service.FBallReplyService;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Domain.MaliciousReply;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyPageItemResDto;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Repository.MaliciousReplyDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaliciousReplyBeforeJudgmentSearchService extends MaliciousReplySearchService{

    final MaliciousReplyDataRepository maliciousReplyDataRepository;

    public MaliciousReplyBeforeJudgmentSearchService(FBallReplyService fBallReplyService,
                                                     MaliciousReplyDataRepository maliciousReplyDataRepository) {
        super(fBallReplyService);
        this.maliciousReplyDataRepository = maliciousReplyDataRepository;
    }

    @Override
    public Page<MaliciousReplyPageItemResDto> search(Pageable pageable) {
        Page<MaliciousReply> byJudgmentUidNull = this.maliciousReplyDataRepository.findByJudgmentUidNull(pageable);
        return makeMaliciousReplyPageItemResDto(byJudgmentUidNull);
    }
}
