package com.wing.backend.forutonamanager.manager.EventManagement.Repository;

import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventManagementDataRepository extends JpaRepository<EventManagement,Integer> {
}
