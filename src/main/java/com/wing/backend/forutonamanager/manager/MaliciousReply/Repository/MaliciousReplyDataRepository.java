package com.wing.backend.forutonamanager.manager.MaliciousReply.Repository;

import com.wing.backend.forutonamanager.manager.MaliciousReply.Domain.MaliciousReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaliciousReplyDataRepository extends JpaRepository<MaliciousReply,Integer> {
    Page<MaliciousReply> findByJudgmentUidNull(Pageable pageable);
    Page<MaliciousReply> findByFalseReportFlagTrue(Pageable pageable);
    Page<MaliciousReply> findByMaliciousContentFlagTrue(Pageable pageable);
}

