package com.wing.backend.forutonamanager.manager.EventManagement.Dto;

import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventCategoryType;
import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventManagement;
import com.wing.backend.forutonamanager.manager.MUserInfo.Dto.MUserInfoResDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventManagementResDto {
    Integer idx;
    EventCategoryType category;
    String title;
    String subTitle;
    Boolean isOpen;
    Boolean allowComments;
    LocalDateTime eventStartDateTime;
    LocalDateTime eventEndDateTime;
    Integer views;
    Double eventStartPositionLat;
    Double eventStartPositionLng;
    String detailAddress;
    String listThumbnail;
    String detailPageThumbnail;
    String detailedDescription;
    String webViewArea;
    MUserInfoResDto writerUid;

    public static EventManagementResDto fromEventManagement(EventManagement eventManagement){
        EventManagementResDto resDto  = new EventManagementResDto();

        resDto.idx = eventManagement.getIdx();
        resDto.category = eventManagement.getCategory();
        resDto.title = eventManagement.getTitle();
        resDto.subTitle = eventManagement.getSubTitle();
        resDto.isOpen = eventManagement.getIsOpen();
        resDto.allowComments = eventManagement.getAllowComments();
        resDto.eventStartDateTime = eventManagement.getEventStartDateTime();
        resDto.eventEndDateTime = eventManagement.getEventEndDateTime();
        resDto.views = eventManagement.getViews();
        resDto.eventStartPositionLat = eventManagement.getEventStartPositionLat();
        resDto.eventStartPositionLng = eventManagement.getEventStartPositionLng();
        resDto.detailAddress = eventManagement.getDetailAddress();
        resDto.listThumbnail = eventManagement.getListThumbnail();
        resDto.detailPageThumbnail = eventManagement.getDetailPageThumbnail();
        resDto.detailedDescription = eventManagement.getDetailedDescription();
        resDto.webViewArea = eventManagement.getWebViewArea();
        resDto.writerUid = MUserInfoResDto.fromUserInfoResDto(eventManagement.getWriterUid());

        return resDto;
    }
}

