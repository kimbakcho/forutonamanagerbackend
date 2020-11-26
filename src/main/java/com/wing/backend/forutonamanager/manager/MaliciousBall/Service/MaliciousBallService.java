package com.wing.backend.forutonamanager.manager.MaliciousBall.Service;

import com.wing.backend.forutonamanager.manager.MaliciousBall.Repository.MaliciousBallDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MaliciousBallService {

    final MaliciousBallDataRepository maliciousBallDataRepository;

}
