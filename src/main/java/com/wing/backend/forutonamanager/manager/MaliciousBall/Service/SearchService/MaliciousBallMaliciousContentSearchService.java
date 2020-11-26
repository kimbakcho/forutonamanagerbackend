package com.wing.backend.forutonamanager.manager.MaliciousBall.Service.SearchService;

import com.wing.backend.forutonamanager.forutona.FBall.Service.FBallService;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousBall;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Dto.MaliciousBallResDto;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Repository.MaliciousBallDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaliciousBallMaliciousContentSearchService extends MaliciousBallSearchService {

    final MaliciousBallDataRepository maliciousBallDataRepository;

    public MaliciousBallMaliciousContentSearchService(FBallService fBallService, MaliciousBallDataRepository maliciousBallDataRepository) {
        super(fBallService);
        this.maliciousBallDataRepository = maliciousBallDataRepository;
    }

    @Override
    public Page<MaliciousBallResDto> search(Pageable pageable) {
        Page<MaliciousBall> pageMaliciousContent = maliciousBallDataRepository.findByMaliciousContentFlagTrue(pageable);
        return makePageMaliciousBallResDto(pageMaliciousContent);
    }



}
