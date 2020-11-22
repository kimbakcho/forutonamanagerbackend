package com.wing.backend.forutonamanager.manager.EventManagement.Service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.wing.backend.forutonamanager.Google.GoogleStorageAdmin;
import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventManagement;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementInsertReqDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementResDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementUpdateReqDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Repository.EventManagementDataRepository;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EventManagementService {

    final EventManagementDataRepository eventManagementDataRepository;

    final MUserInfoDataRepository mUserInfoDataRepository;

    final GoogleStorageAdmin googleStorageAdmin;

    public EventManagementResDto insertEventManagement(CustomOAuth2User customOAuth2User,
                                                       EventManagementInsertReqDto reqDto) {

        MUserInfo writer = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();

        EventManagement event = EventManagement.builder()
                .category(reqDto.getCategory())
                .title(reqDto.getTitle())
                .subTitle(reqDto.getSubTitle())
                .allowComments(reqDto.getAllowComments())
                .detailedDescription(reqDto.getDetailedDescription())
                .eventStartDateTime(reqDto.getEventStartDateTime())
                .eventEndDateTime(reqDto.getEventEndDateTime())
                .eventStarPositionLng(reqDto.getEventStartPositionLng())
                .eventStartPositionLat(reqDto.getEventStartPositionLat())
                .detailAddress(reqDto.getDetailAddress())
                .isOpen(reqDto.getIsOpen())
                .writerUid(writer)
                .build();

        return EventManagementResDto.fromEventManagement(eventManagementDataRepository.save(event));
    }

    public EventManagementResDto getEventManagement(Integer eventIdx) {
        EventManagement eventManagement = eventManagementDataRepository.findById(eventIdx).get();
        return EventManagementResDto.fromEventManagement(eventManagement);
    }


    public EventManagementResDto updateListThumbnail(MultipartFile listThumbnail,
                                                     Integer eventIdx) throws IOException {
        EventManagement eventManagement = eventManagementDataRepository.findById(eventIdx).get();

        String listThumbnailUrl = eventManagement.getListThumbnail();
        if(listThumbnailUrl != null && listThumbnailUrl.length() > 0){
            this.deleteEventResource(eventManagement.getListThumbnail());
        }

        if(listThumbnail != null){
            String imageUrl = uploadEventResource(listThumbnail, eventIdx);
            eventManagement.setListThumbnail(imageUrl);
        }else {
            eventManagement.setListThumbnail(null);
        }

        return EventManagementResDto.fromEventManagement(eventManagement);
    }

    public EventManagementResDto updateDetailPageThumbnail(MultipartFile detailPageThumbnail, Integer eventIdx) throws IOException {
        EventManagement eventManagement = eventManagementDataRepository.findById(eventIdx).get();

        String detailPageThumbnailUrl = eventManagement.getDetailPageThumbnail();
        if(detailPageThumbnailUrl != null && detailPageThumbnailUrl.length() > 0){
            this.deleteEventResource(eventManagement.getDetailPageThumbnail());
        }

        if(detailPageThumbnail != null){
            String imageUrl = uploadEventResource(detailPageThumbnail, eventIdx);
            eventManagement.setDetailPageThumbnail(imageUrl);
        }else {
            eventManagement.setDetailPageThumbnail(null);
        }

        return EventManagementResDto.fromEventManagement(eventManagement);
    }

    public EventManagementResDto updateWebViewArea(MultipartFile webViewArea, Integer eventIdx) throws IOException {
        EventManagement eventManagement = eventManagementDataRepository.findById(eventIdx).get();

        String webViewUrl = eventManagement.getWebViewArea();
        if(webViewUrl != null && webViewUrl.length() > 0){
            this.deleteEventResource(eventManagement.getWebViewArea());
        }

        if(webViewArea != null){
            String htmlUrl = uploadEventResource(webViewArea, eventIdx);
            eventManagement.setWebViewArea(htmlUrl);
        }else {
            eventManagement.setWebViewArea(null);
        }

        return EventManagementResDto.fromEventManagement(eventManagement);
    }

    private String uploadEventResource(MultipartFile resourceFile, Integer eventIdx) throws IOException {
        Storage storage = googleStorageAdmin.GetStorageInstance();
        int extentIndex = resourceFile.getOriginalFilename().lastIndexOf(".");
        String extent = resourceFile.getOriginalFilename().substring(extentIndex);
        String Uuid = UUID.randomUUID().toString();
        BlobId blobId = BlobId.of("publicforutona", "eventresource/" + eventIdx + "/" + Uuid + extent);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(resourceFile.getContentType()).build();
        storage.create(blobInfo, resourceFile.getBytes());
        String imageUrl = "https://storage.googleapis.com/publicforutona/eventresource/" + eventIdx + "/" + Uuid + extent;
        return imageUrl;
    }

    private void deleteEventResource(String url) throws IOException {
        Storage storage = googleStorageAdmin.GetStorageInstance();
        String replaceUrl = url.replace("https://storage.googleapis.com/publicforutona/", "");
        BlobId blobId = BlobId.of("publicforutona", replaceUrl);
        storage.delete(blobId);
    }



    public EventManagementResDto updateEventManagement(CustomOAuth2User customOAuth2User,
                                                       EventManagementUpdateReqDto reqDto) {
        MUserInfo writer = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();
        EventManagement eventManagement = eventManagementDataRepository.findById(reqDto.getIdx()).get();
        eventManagement.setTitle(reqDto.getTitle());
        eventManagement.setSubTitle(reqDto.getSubTitle());
        eventManagement.setCategory(reqDto.getCategory());
        eventManagement.setDetailedDescription(reqDto.getDetailedDescription());
        eventManagement.setEventStartDateTime(reqDto.getEventStartDateTime());
        eventManagement.setEventEndDateTime(reqDto.getEventEndDateTime());
        eventManagement.setEventStarPositionLng(reqDto.getEventStartPositionLng());
        eventManagement.setEventStartPositionLat(reqDto.getEventStartPositionLat());
        eventManagement.setDetailAddress(reqDto.getDetailAddress());
        eventManagement.setAllowComments(reqDto.getAllowComments());
        eventManagement.setOpen(reqDto.getIsOpen());
        eventManagement.setWriterUid(writer);
        return EventManagementResDto.fromEventManagement(eventManagement);
    }

    public void deleteEventManagement(Integer idx) throws IOException {
        EventManagement eventManagement = eventManagementDataRepository.findById(idx).get();
        if(eventManagement.getListThumbnail() != null && eventManagement.getListThumbnail().length()> 0){
            deleteEventResource(eventManagement.getListThumbnail());
        }
        if(eventManagement.getDetailPageThumbnail() != null && eventManagement.getDetailPageThumbnail().length() > 0){
            deleteEventResource(eventManagement.getDetailPageThumbnail());
        }
        if(eventManagement.getWebViewArea() != null && eventManagement.getWebViewArea().length() > 0){
            deleteEventResource(eventManagement.getWebViewArea());
        }
        eventManagementDataRepository.deleteById(idx);
    }
}
