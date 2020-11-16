package com.wing.backend.forutonamanager.manager.EventManagement.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vividsolutions.jts.geom.Point;
import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventCategoryType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class EventManagementInsertReqDto {
    EventCategoryType category;
    String title;
    String subTitle;
    Boolean isOpen;
    Boolean allowComments;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime eventStartDateTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime eventEndDateTime;
    Double eventStartPositionLat;
    Double eventStarPositionLng;
    String detailedDescription;
}
