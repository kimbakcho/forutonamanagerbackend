package com.wing.backend.forutonamanager.manager.EventManagement.Domain;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class EventManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;
    EventCategoryType category;
    String title;
    String subTitle;
    Boolean isOpen;
    Boolean allowComments;
    LocalDateTime eventStartDateTime;
    LocalDateTime eventEndDateTime;
    @Column(columnDefinition = "geometry(Point,4326)")
    Point eventStartPosition;
    Double eventStartPositionLat;
    Double eventStarPositionLng;
    String detailAddress;
    String listThumbnail;

    String detailPageThumbnail;
    String detailedDescription;
    String webViewArea;

    @JoinColumn(name = "writerUid")
    @ManyToOne
    MUserInfo writerUid;

    @Builder
    public EventManagement(EventCategoryType category, String title,
                           String subTitle, Boolean isOpen,
                           Boolean allowComments, LocalDateTime eventStartDateTime,
                           LocalDateTime eventEndDateTime, Double eventStartPositionLat,
                           Double eventStarPositionLng,String detailAddress,
                           String detailedDescription,
                           MUserInfo writerUid) {
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.isOpen = isOpen;
        this.allowComments = allowComments;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
        this.eventStartPositionLat = eventStartPositionLat;
        this.eventStarPositionLng = eventStarPositionLng;
        this.eventStartPosition = getPlacePoint(eventStarPositionLng,eventStartPositionLat);
        this.detailAddress = detailAddress;
        this.detailedDescription = detailedDescription;
        this.writerUid = writerUid;
    }

    Point getPlacePoint(double longitude, double latitude) {
        GeometryFactory geomFactory = new GeometryFactory();
        Point point = geomFactory.createPoint(new Coordinate(longitude, latitude));
        point.setSRID(4326);
        return point;
    }

    public void setListThumbnail(String listThumbnail) {
        this.listThumbnail = listThumbnail;
    }

    public void setDetailPageThumbnail(String detailPageThumbnail) {
        this.detailPageThumbnail = detailPageThumbnail;
    }

    public void setWebViewArea(String webViewArea) {
        this.webViewArea = webViewArea;
    }


    public void setCategory(EventCategoryType category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public void setAllowComments(Boolean allowComments) {
        this.allowComments = allowComments;
    }

    public void setEventStartDateTime(LocalDateTime eventStartDateTime) {
        this.eventStartDateTime = eventStartDateTime;
    }

    public void setEventEndDateTime(LocalDateTime eventEndDateTime) {
        this.eventEndDateTime = eventEndDateTime;
    }

    public void setEventStartPositionLat(Double eventStartPositionLat) {
        this.eventStartPosition = getPlacePoint(eventStarPositionLng,eventStartPositionLat);
        this.eventStartPositionLat = eventStartPositionLat;
    }

    public void setEventStarPositionLng(Double eventStarPositionLng) {
        this.eventStartPosition = getPlacePoint(eventStarPositionLng,eventStartPositionLat);
        this.eventStarPositionLng = eventStarPositionLng;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public void setWriterUid(MUserInfo writerUid) {
        this.writerUid = writerUid;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
