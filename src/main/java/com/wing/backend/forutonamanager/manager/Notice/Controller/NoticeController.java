package com.wing.backend.forutonamanager.manager.Notice.Controller;

import com.wing.backend.forutonamanager.manager.Notice.Dto.InsertNoticeReqDto;
import com.wing.backend.forutonamanager.manager.Notice.Dto.NoticeResDto;
import com.wing.backend.forutonamanager.manager.Notice.Dto.UpdateNoticeReqDto;
import com.wing.backend.forutonamanager.manager.Notice.Repository.NoticeDataRepository;
import com.wing.backend.forutonamanager.manager.Notice.Service.NoticeService;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    final NoticeService noticeService;

    @GetMapping
    public Page<NoticeResDto> getNotices(Pageable pageable){
        return noticeService.getNotices(pageable);
    }

    @PostMapping
    public NoticeResDto insertNotice(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestBody InsertNoticeReqDto insertNoticeReqDto){
        return noticeService.insertNotice(customOAuth2User,insertNoticeReqDto);
    }

    @PutMapping
    public NoticeResDto updateNotice(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestBody UpdateNoticeReqDto updateNoticeReqDto){
        return noticeService.updateNotice(customOAuth2User,updateNoticeReqDto);
    }

    @DeleteMapping
    public void deleteNotice(Integer idx){
        noticeService.deleteNotice(idx);
    }

}
