package com.wing.backend.forutonamanager.manager.MaliciousProfile.Service;

import com.wing.backend.forutonamanager.manager.MaliciousProfile.Domain.MaliciousProfile;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfileResDto;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Repository.MaliciousProfileDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MaliciousProfileService {

    final MaliciousProfileDataRepository maliciousProfileDataRepository;

    public MaliciousProfileResDto getMaliciousProfile(Integer idx){
        MaliciousProfile maliciousProfile = maliciousProfileDataRepository.findById(idx).get();
        return MaliciousProfileResDto.fromMaliciousProfile(maliciousProfile);
    }
}
