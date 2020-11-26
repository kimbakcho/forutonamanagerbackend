package com.wing.backend.forutonamanager.forutona.FBall.Service;

import com.wing.backend.forutonamanager.forutona.FBall.Dto.FBallSimpleResDto;
import com.wing.backend.forutonamanager.forutona.FBall.Repository.FBallQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FBallService {

    final FBallQueryRepository fBallQueryRepository;

    public List<FBallSimpleResDto> getBallUuidsToFBallSimpleData(List<String> ballUuids) {
        return fBallQueryRepository.findByBallUuidsToFBallSimpleData(ballUuids);
    }
}
