package com.wing.backend.forutonamanager.manager.Notice.Service;

import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Notice.Domain.Notice;
import com.wing.backend.forutonamanager.manager.Notice.Dto.InsertNoticeReqDto;
import com.wing.backend.forutonamanager.manager.Notice.Dto.NoticeResDto;
import com.wing.backend.forutonamanager.manager.Notice.Dto.UpdateNoticeReqDto;
import com.wing.backend.forutonamanager.manager.Notice.Repository.NoticeDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeService {
    final NoticeDataRepository noticeDataRepository;

    final MUserInfoDataRepository mUserInfoDataRepository;

    public Page<NoticeResDto> getNotices(Pageable pageable) {
        Page<Notice> all = noticeDataRepository.findAll(pageable);
        return all.map(x -> NoticeResDto.NoticeResDtoFromNotice(x));
    }

    public NoticeResDto insertNotice(CustomOAuth2User customOAuth2User, InsertNoticeReqDto insertNoticeReqDto) {
        MUserInfo mUserInfo = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();

        Notice notice = Notice.builder()
                .title(insertNoticeReqDto.getTitle())
                .content(insertNoticeReqDto.getContent())
                .openFlag(insertNoticeReqDto.getOpenFlag())
                .writerUid(mUserInfo)
                .modifyDate(LocalDateTime.now())
                .build();

        Notice saveItem = noticeDataRepository.save(notice);

        return NoticeResDto.NoticeResDtoFromNotice(saveItem);
    }


    public NoticeResDto updateNotice(CustomOAuth2User customOAuth2User,
                                     UpdateNoticeReqDto updateNoticeReqDto) {
        MUserInfo mUserInfo = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();
        Notice notice = noticeDataRepository.findById(updateNoticeReqDto.getIdx()).get();
        notice.setTitle(updateNoticeReqDto.getTitle());
        notice.setContent(updateNoticeReqDto.getContent());
        notice.setOpenFlag(updateNoticeReqDto.getOpenFlag());
        notice.setModifyDate(LocalDateTime.now());
        notice.setWriterUid(mUserInfo);
        return NoticeResDto.NoticeResDtoFromNotice(notice);
    }


    public void deleteNotice(Integer idx) {
        noticeDataRepository.deleteById(idx);
    }
}
