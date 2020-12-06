package com.wing.backend.forutonamanager.manager.MaliciousProfile.Repository;


import com.wing.backend.forutonamanager.manager.MaliciousProfile.Domain.MaliciousProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaliciousProfileDataRepository extends JpaRepository<MaliciousProfile,Integer> {
    Page<MaliciousProfile> findByJudgmentUidNull(Pageable pageable);
    Page<MaliciousProfile> findByMaliciousContentFlagTrue(Pageable pageable);
    Page<MaliciousProfile> findByFalseReportFlagTrue(Pageable pageable);
}
