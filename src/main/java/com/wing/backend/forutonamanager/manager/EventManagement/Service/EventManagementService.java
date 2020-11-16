package com.wing.backend.forutonamanager.manager.EventManagement.Service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.wing.backend.forutonamanager.Google.GoogleStorageAdmin;
import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventManagement;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementInsertReqDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementResDto;
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
                                                       EventManagementInsertReqDto reqDto){

        MUserInfo writer = mUserInfoDataRepository.findById(customOAuth2User.getMUserInfo().getUid()).get();

        EventManagement event = EventManagement.builder()
                .category(reqDto.getCategory())
                .title(reqDto.getTitle())
                .subTitle(reqDto.getSubTitle())
                .allowComments(reqDto.getAllowComments())
                .detailedDescription(reqDto.getDetailedDescription())
                .eventStartDateTime(reqDto.getEventStartDateTime())
                .eventEndDateTime(reqDto.getEventEndDateTime())
                .eventStarPositionLng(reqDto.getEventStarPositionLng())
                .eventStartPositionLat(reqDto.getEventStartPositionLat())
                .isOpen(reqDto.getIsOpen())
                .writerUid(writer)
                .build();

        return EventManagementResDto.fromEventManagement(eventManagementDataRepository.save(event));
    }


    public EventManagementResDto updateListThumbnail(MultipartFile listThumbnail,
                                                     Integer eventIdx) throws IOException {
        String imageUrl = uploadEventResource(listThumbnail, eventIdx);
        EventManagement eventManagement = eventManagementDataRepository.findById(eventIdx).get();
        eventManagement.setListThumbnail(imageUrl);
        return EventManagementResDto.fromEventManagement(eventManagement);
    }

    public EventManagementResDto updateDetailPageThumbnail(MultipartFile detailPageThumbnail, Integer eventIdx) throws IOException {
        String imageUrl = uploadEventResource(detailPageThumbnail, eventIdx);
        EventManagement eventManagement = eventManagementDataRepository.findById(eventIdx).get();
        eventManagement.setDetailPageThumbnail(imageUrl);
        return EventManagementResDto.fromEventManagement(eventManagement);
    }

    public EventManagementResDto updateWebViewArea(MultipartFile webViewArea, Integer eventIdx) throws IOException {
        String htmlUrl = uploadEventResource(webViewArea, eventIdx);
        EventManagement eventManagement = eventManagementDataRepository.findById(eventIdx).get();
        eventManagement.setWebViewArea(htmlUrl);
        return EventManagementResDto.fromEventManagement(eventManagement);
    }

    private String uploadEventResource(MultipartFile resourceFile, Integer eventIdx) throws IOException {
        Storage storage = googleStorageAdmin.GetStorageInstance();
        int extentIndex = resourceFile.getOriginalFilename().lastIndexOf(".");
        String extent = resourceFile.getOriginalFilename().substring(extentIndex);
        String Uuid = UUID.randomUUID().toString();
        BlobId blobId = BlobId.of("publicforutona", "eventresource/"+ eventIdx +"/"+Uuid+extent);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(resourceFile.getContentType()).build();
        storage.create(blobInfo, resourceFile.getBytes() );
        String imageUrl = "https://storage.googleapis.com/publicforutona/eventresource/" + eventIdx +"/"+Uuid+extent;
        return imageUrl;
    }



}
