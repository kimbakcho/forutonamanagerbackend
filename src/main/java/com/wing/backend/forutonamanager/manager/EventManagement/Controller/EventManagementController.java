package com.wing.backend.forutonamanager.manager.EventManagement.Controller;

import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventSearchType;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementInsertReqDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementResDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Service.EventManagementSearch.EventManagementSearchService;
import com.wing.backend.forutonamanager.manager.EventManagement.Service.EventManagementSearch.EventManagementSearchServiceFactory;
import com.wing.backend.forutonamanager.manager.EventManagement.Service.EventManagementService;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eventManagement")
public class EventManagementController {

    final EventManagementService eventManagementService;

    final EventManagementSearchServiceFactory eventManagementSearchServiceFactory;

    @PostMapping
    EventManagementResDto insertEventManagement(
            @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
            @RequestBody EventManagementInsertReqDto eventManagementInsertReqDto){

        return eventManagementService.insertEventManagement(customOAuth2User,eventManagementInsertReqDto);
    }

    @PostMapping("/listThumbnail")
    EventManagementResDto updateListThumbnail(MultipartFile listThumbnail,Integer eventIdx) throws IOException {
        return eventManagementService.updateListThumbnail(listThumbnail,eventIdx);
    }

    @PostMapping("/detailPageThumbnail")
    EventManagementResDto updateDetailPageThumbnail(MultipartFile detailPageThumbnail,Integer eventIdx) throws IOException {
        return eventManagementService.updateDetailPageThumbnail(detailPageThumbnail,eventIdx);
    }

    @PostMapping("/webViewArea")
    EventManagementResDto updateWebViewArea(MultipartFile webViewArea,Integer eventIdx) throws IOException {
        return eventManagementService.updateWebViewArea(webViewArea,eventIdx);
    }

    @GetMapping
    Page<EventManagementResDto> getEventManagement(EventSearchType eventSearchType, Pageable pageable) throws Exception {
        EventManagementSearchService instance = eventManagementSearchServiceFactory.getInstance(eventSearchType);
        return instance.search(pageable);
    }
}
