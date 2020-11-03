package com.wing.backend.forutonamanager.manager.TermsConditions.Repository;

import com.wing.backend.forutonamanager.manager.TermsConditions.Domain.TermsConditions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TermsConditionsDataRepository extends JpaRepository<TermsConditions,Integer> {
    Optional<TermsConditions> findByTermsName(String termsName);
}
