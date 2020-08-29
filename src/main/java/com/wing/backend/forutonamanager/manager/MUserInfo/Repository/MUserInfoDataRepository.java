package com.wing.backend.forutonamanager.manager.MUserInfo.Repository;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MUserInfoDataRepository extends JpaRepository<MUserInfo,String> {

}
