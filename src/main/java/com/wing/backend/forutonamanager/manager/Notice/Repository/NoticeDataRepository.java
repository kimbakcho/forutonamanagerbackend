package com.wing.backend.forutonamanager.manager.Notice.Repository;

import com.wing.backend.forutonamanager.manager.Notice.Domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeDataRepository extends JpaRepository<Notice,Integer> {

}
