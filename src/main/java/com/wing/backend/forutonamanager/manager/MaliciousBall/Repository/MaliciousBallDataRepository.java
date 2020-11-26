package com.wing.backend.forutonamanager.manager.MaliciousBall.Repository;

import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaliciousBallDataRepository extends JpaRepository<MaliciousBall,Integer> {
    Page<MaliciousBall> findByJudgmentUidNull(Pageable pageable);
    Page<MaliciousBall> findByMaliciousContentFlagTrue(Pageable pageable);
    Page<MaliciousBall> findByFalseReportFlagTrue(Pageable pageable);
}
