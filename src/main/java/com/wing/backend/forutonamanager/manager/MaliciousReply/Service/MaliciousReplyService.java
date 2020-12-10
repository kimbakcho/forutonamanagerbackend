package com.wing.backend.forutonamanager.manager.MaliciousReply.Service;

import com.wing.backend.forutonamanager.manager.MaliciousReply.Domain.MaliciousReply;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyResDto;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Repository.MaliciousReplyDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MaliciousReplyService {

    final MaliciousReplyDataRepository maliciousReplyDataRepository;

    public MaliciousReplyResDto getMaliciousReply(Integer idx){
        MaliciousReply maliciousReply = maliciousReplyDataRepository.findById(idx).get();
        return MaliciousReplyResDto.fromMaliciousReply(maliciousReply);
    }


}
