package com.wing.backend.forutonamanager.manager.Notice.Dto;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import com.wing.backend.forutonamanager.manager.Notice.Domain.Notice;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeResDto {
    Integer idx;
    String title;
    String content;
    Boolean openFlag;
    MUserInfoResDto writerUid;
    LocalDateTime modifyDate;

    public static NoticeResDto NoticeResDtoFromNotice(Notice notice){
        NoticeResDto noticeResDto = new NoticeResDto();
        noticeResDto.idx = notice.getIdx();
        noticeResDto.title = notice.getTitle();
        noticeResDto.openFlag = notice.getOpenFlag();
        noticeResDto.writerUid = MUserInfoResDto.fromUserInfoResDto(notice.getWriterUid());
        noticeResDto.modifyDate = notice.getModifyDate();
        return noticeResDto;
    }

}
