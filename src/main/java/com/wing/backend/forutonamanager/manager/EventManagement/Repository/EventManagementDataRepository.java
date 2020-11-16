package com.wing.backend.forutonamanager.manager.EventManagement.Repository;

import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EventManagementDataRepository extends JpaRepository<EventManagement,Integer> {

    Page<EventManagement> findByEventStartDateTimeBefore(LocalDateTime now, Pageable pageable);
    Page<EventManagement> findByEventEndDateTimeBefore(LocalDateTime now, Pageable pageable);
    Page<EventManagement> findByEventStartDateTimeAfterAndEventEndDateTimeBefore(LocalDateTime now1,LocalDateTime now2,Pageable pageable);
}
