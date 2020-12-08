package com.wing.backend.forutonamanager.forutona.FBallReply.Service;

import com.wing.backend.forutonamanager.forutona.FBallReply.Domain.FBallReply;
import com.wing.backend.forutonamanager.forutona.FBallReply.Dto.FBallReplyResDto;
import com.wing.backend.forutonamanager.forutona.FBallReply.Repository.FBallReplyQueryRepository;
import com.wing.backend.forutonamanager.forutona.FBallReply.Repository.FBallReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(transactionManager = "forutonaAppTransactionManager")
@RequiredArgsConstructor
public class FBallReplyService {

    final FBallReplyRepository fBallReplyRepository;

    final FBallReplyQueryRepository fBallReplyQueryRepository;

    public FBallReplyResDto getReply(String replyUuid){
        return FBallReplyResDto.fromFBallReply(fBallReplyRepository.findById(replyUuid).get());
    }

    public List<FBallReplyResDto> replyUuidToFBallReplyResDtos(List<String> replyUuids){
        List<FBallReply> byReplyUuidToFBallReply = fBallReplyQueryRepository.findByReplyUuidToFBallReply(replyUuids);
        return byReplyUuidToFBallReply.stream().map(x -> FBallReplyResDto.fromFBallReply(x)).collect(Collectors.toList());
    }
}
