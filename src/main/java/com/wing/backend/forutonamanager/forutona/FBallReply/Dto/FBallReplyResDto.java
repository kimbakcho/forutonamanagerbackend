package com.wing.backend.forutonamanager.forutona.FBallReply.Dto;

import com.wing.backend.forutonamanager.forutona.FBall.Domain.FBall;
import com.wing.backend.forutonamanager.forutona.FBall.Dto.FBallResDto;
import com.wing.backend.forutonamanager.forutona.FBallReply.Domain.FBallReply;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Dto.FUserInfoResDto;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class FBallReplyResDto {
    String replyUuid;
    FBallResDto ballUuid;
    FUserInfoResDto uid;
    Integer replyNumber;
    Integer replySort;
    Integer replyDepth;
    String replyText;
    LocalDateTime replyUploadDateTime;
    LocalDateTime replyUpdateDateTime;
    Boolean deleteFlag;

    public static FBallReplyResDto fromFBallReply(FBallReply fBallReply){
        FBallReplyResDto fBallReplyResDto = new FBallReplyResDto();
        fBallReplyResDto.replyUuid = fBallReply.getReplyUuid();
        fBallReplyResDto.ballUuid = FBallResDto.fromFBall(fBallReply.getBallUuid());
        fBallReplyResDto.uid = FUserInfoResDto.fromFUserInfo(fBallReply.getUid());
        fBallReplyResDto.replyNumber = fBallReply.getReplyNumber();
        fBallReplyResDto.replySort = fBallReply.getReplySort();
        fBallReplyResDto.replyDepth = fBallReply.getReplyDepth();
        fBallReplyResDto.replyText = fBallReply.getReplyText();
        fBallReplyResDto.replyUploadDateTime = fBallReply.getReplyUploadDateTime();
        fBallReplyResDto.replyUpdateDateTime = fBallReply.getReplyUpdateDateTime();
        fBallReplyResDto.deleteFlag = fBallReply.getDeleteFlag();
        return fBallReplyResDto;
    }
}
