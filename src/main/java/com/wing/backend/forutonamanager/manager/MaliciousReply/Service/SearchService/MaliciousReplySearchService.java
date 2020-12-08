package com.wing.backend.forutonamanager.manager.MaliciousReply.Service.SearchService;

import com.wing.backend.forutonamanager.forutona.FBallReply.Dto.FBallReplyResDto;
import com.wing.backend.forutonamanager.forutona.FBallReply.Service.FBallReplyService;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Domain.MaliciousProfile;
import com.wing.backend.forutonamanager.manager.MaliciousProfile.Dto.MaliciousProfilePageItemResDto;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Domain.MaliciousReply;
import com.wing.backend.forutonamanager.manager.MaliciousReply.Dto.MaliciousReplyPageItemResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class MaliciousReplySearchService {

    final FBallReplyService fBallReplyService;

    public abstract Page<MaliciousReplyPageItemResDto> search(Pageable pageable);

    protected Page<MaliciousReplyPageItemResDto> makeMaliciousReplyPageItemResDto(Page<MaliciousReply> maliciousReplies){

        List<String> replyCollect = maliciousReplies.getContent()
                .stream()
                .map(x -> x.getReplyUuid())
                .collect(Collectors.toList());

        List<FBallReplyResDto> fBallReplyResDtos = fBallReplyService.replyUuidToFBallReplyResDtos(replyCollect);

        Page<MaliciousReplyPageItemResDto> maliciousReplyPage =  maliciousReplies.map(x->{

            Optional<FBallReplyResDto> optionalFBallReplyResDto = fBallReplyResDtos.stream().filter(p -> p.getReplyUuid().equals(x.getReplyUuid()))
                    .findFirst();

            MaliciousReplyPageItemResDto maliciousReplyPageItemResDto = new MaliciousReplyPageItemResDto();
            maliciousReplyPageItemResDto.setIdx(x.getIdx());
            maliciousReplyPageItemResDto.setFalseReportFlag(x.getFalseReportFlag());
            maliciousReplyPageItemResDto.setMaliciousContentFlag(x.getMaliciousContentFlag());
            maliciousReplyPageItemResDto.setTotalNumberReports(x.getTotalNumberReports());
            maliciousReplyPageItemResDto.setReplyUuid(x.getReplyUuid());
            if(optionalFBallReplyResDto.isPresent()){
                FBallReplyResDto fBallReplyResDto = optionalFBallReplyResDto.get();
                maliciousReplyPageItemResDto.setReplyText(fBallReplyResDto.getReplyText());
                maliciousReplyPageItemResDto.setReplyUploadTime(fBallReplyResDto.getReplyUploadDateTime());
                maliciousReplyPageItemResDto.setReplyUserNickName(fBallReplyResDto.getUid().getNickName());
            }else {
                maliciousReplyPageItemResDto.setReplyText("don't have");
            }
            return maliciousReplyPageItemResDto;
        });

        return maliciousReplyPage;
    }

}
