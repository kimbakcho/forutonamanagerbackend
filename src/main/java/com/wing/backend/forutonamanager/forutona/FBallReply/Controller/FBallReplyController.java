package com.wing.backend.forutonamanager.forutona.FBallReply.Controller;

import com.wing.backend.forutonamanager.forutona.FBallReply.Dto.FBallReplyResDto;
import com.wing.backend.forutonamanager.forutona.FBallReply.Service.FBallReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/FBallReply")
public class FBallReplyController {

    final FBallReplyService fBallReplyService;

    @GetMapping("/ReplyUuid")
    public FBallReplyResDto getItem(String replyUuid){
        return fBallReplyService.getReply(replyUuid);
    }


}
