package com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Repository;

import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.MaliciousType;
import com.wing.backend.forutonamanager.forutona.UserInfoMaliciousHistory.Domain.UserInfoMaliciousHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UserInfoMaliciousHistoryDataRepository extends JpaRepository<UserInfoMaliciousHistory,Integer> {
    Long countByMaliciousDateTimeAfterAndMaliciousType(LocalDateTime startDateTime, MaliciousType maliciousType);
}
