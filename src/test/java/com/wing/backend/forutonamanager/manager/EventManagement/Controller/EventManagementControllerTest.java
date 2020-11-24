package com.wing.backend.forutonamanager.manager.EventManagement.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wing.backend.forutonamanager.SecurityTestUtil.TestBase;
import com.wing.backend.forutonamanager.SecurityTestUtil.WithMockCustomUser;
import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventCategoryType;
import com.wing.backend.forutonamanager.manager.EventManagement.Domain.EventManagement;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementInsertReqDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Dto.EventManagementUpdateReqDto;
import com.wing.backend.forutonamanager.manager.EventManagement.Repository.EventManagementDataRepository;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.relaxedRequestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EventManagementControllerTest extends TestBase {

    @Autowired
    EventManagementDataRepository eventManagementDataRepository;

    @Autowired
    MUserInfoDataRepository mUserInfoDataRepository;

    @Test
    @WithMockCustomUser
    void insertEventManagement() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        EventManagementInsertReqDto reqDto = new EventManagementInsertReqDto();
        reqDto.setTitle("TEST Title");
        reqDto.setSubTitle("TEST SubTitle");
        reqDto.setAllowComments(true);
        reqDto.setCategory(EventCategoryType.DEFAULT);
        reqDto.setDetailedDescription("TEST");
        reqDto.setDetailAddress("detail Address");
        reqDto.setEventEndDateTime(LocalDateTime.now().plusDays(1));
        reqDto.setEventStartDateTime(LocalDateTime.now());
        reqDto.setEventStartPositionLng(126.18347167968749);
        reqDto.setEventStartPositionLat(37.00255267215955);
        reqDto.setIsOpen(true);

        //when
        mockMvc.perform(post("/eventManagement")
                .content(objectMapper.writeValueAsString(reqDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andDo(document("EventManagement", relaxedRequestFields(
                        fieldWithPath("category").description("카테고리"),
                        fieldWithPath("title").description("제목"),
                        fieldWithPath("subTitle").description("부 제목"),
                        fieldWithPath("isOpen").description("공개 여부"),
                        fieldWithPath("allowComments").description("댓글 가능 여부"),
                        fieldWithPath("eventStartDateTime").description("이벤트 시작 시간"),
                        fieldWithPath("eventEndDateTime").description("이벤트 종료 시간"),
                        fieldWithPath("eventStartPositionLat").description("이벤트 시작 위치"),
                        fieldWithPath("eventStartPositionLng").description("이벤트 시작 위치"),
                        fieldWithPath("eventStartPositionLng").description("이벤트 시작 위치"),
                        fieldWithPath("detailedDescription").description("이벤트 상세 설명(HTML)"),
                        fieldWithPath("detailAddress").description("상세 주소")
                        ),relaxedResponseFields(
                        fieldWithPath("idx").description("event 고유 Id"),
                        fieldWithPath("category").description("카테고리"),
                        fieldWithPath("title").description("제목"),
                        fieldWithPath("subTitle").description("부 제목"),
                        fieldWithPath("isOpen").description("공개 여부"),
                        fieldWithPath("allowComments").description("댓글 가능 여부"),
                        fieldWithPath("eventStartDateTime").description("이벤트 시작 시간"),
                        fieldWithPath("eventEndDateTime").description("이벤트 종료 시간"),
                        fieldWithPath("eventStartPositionLat").description("이벤트 시작 위치"),
                        fieldWithPath("eventStartPositionLng").description("이벤트 시작 위치"),
                        fieldWithPath("listThumbnail").description("listThumbnail Url"),
                        fieldWithPath("detailPageThumbnail").description("detailPageThumbnail Url"),
                        fieldWithPath("detailedDescription").description("상세 설명(HTML)"),
                        fieldWithPath("webViewArea").description("HTML 파일 URL"),
                        fieldWithPath("detailAddress").description("상세 주소"),
                        fieldWithPath("writerUid.userName").description("작성자")))
                );

        //then
    }

    @Test
    @WithMockCustomUser
    void updateListThumbnail() throws Exception {
        //given

        MUserInfo writer = mUserInfoDataRepository.findById("forutonatest").get();
        EventManagement event = EventManagement.builder()
                .isOpen(true)
                .eventStartPositionLat(37.00255267215955)
                .eventStarPositionLng(126.18347167968749)
                .eventStartDateTime(LocalDateTime.now())
                .eventEndDateTime(LocalDateTime.now().plusDays(1))
                .detailedDescription("<html><body>test</body></html>")
                .detailAddress("detailAddress")
                .allowComments(true)
                .subTitle("sub Title")
                .title("title")
                .category(EventCategoryType.DEFAULT)
                .writerUid(writer)
                .build();

        EventManagement saveEvent = eventManagementDataRepository.save(event);

        ClassLoader classLoader = getClass().getClassLoader();

        byte[] testThumbnail = Files.readAllBytes(new File(classLoader.getResource(
                "static/Thumbnail.PNG").getFile()).toPath());

        MockMultipartFile firstFile = new MockMultipartFile("listThumbnail",
                "Thumbnail.PNG", "image/png", testThumbnail);


        //when
        mockMvc.perform(multipart("/eventManagement/listThumbnail")
                .file(firstFile)
                .param("eventIdx",saveEvent.getIdx().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @WithMockCustomUser
    void updateDetailPageThumbnail() throws Exception {
        //given

        MUserInfo writer = mUserInfoDataRepository.findById("forutonatest").get();
        EventManagement event = EventManagement.builder()
                .isOpen(true)
                .eventStartPositionLat(37.00255267215955)
                .eventStarPositionLng(126.18347167968749)
                .eventStartDateTime(LocalDateTime.now())
                .eventEndDateTime(LocalDateTime.now().plusDays(1))
                .detailedDescription("<html><body>test</body></html>")
                .detailAddress("detailAddress")
                .allowComments(true)
                .subTitle("sub Title")
                .title("title")
                .category(EventCategoryType.DEFAULT)
                .writerUid(writer)
                .build();

        EventManagement saveEvent = eventManagementDataRepository.save(event);

        ClassLoader classLoader = getClass().getClassLoader();

        byte[] testThumbnail = Files.readAllBytes(new File(classLoader.getResource(
                "static/Thumbnail.PNG").getFile()).toPath());

        MockMultipartFile firstFile = new MockMultipartFile("detailPageThumbnail",
                "Thumbnail.PNG", "image/png", testThumbnail);

        //when
        mockMvc.perform(multipart("/eventManagement/detailPageThumbnail")
                .file(firstFile)
                .param("eventIdx",saveEvent.getIdx().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @WithMockCustomUser
    void webViewArea() throws Exception {
        //given

        MUserInfo writer = mUserInfoDataRepository.findById("forutonatest").get();
        EventManagement event = EventManagement.builder()
                .isOpen(true)
                .eventStartPositionLat(37.00255267215955)
                .eventStarPositionLng(126.18347167968749)
                .eventStartDateTime(LocalDateTime.now())
                .eventEndDateTime(LocalDateTime.now().plusDays(1))
                .detailedDescription("<html><body>test</body></html>")
                .detailAddress("detailAddress")
                .allowComments(true)
                .subTitle("sub Title")
                .title("title")
                .category(EventCategoryType.DEFAULT)
                .writerUid(writer)
                .build();

        EventManagement saveEvent = eventManagementDataRepository.save(event);

        ClassLoader classLoader = getClass().getClassLoader();

        byte[] html = classLoader.getResourceAsStream(
                "static/testwebview.html").readAllBytes();

        MockMultipartFile firstFile = new MockMultipartFile("webViewArea",
                "testwebview.html", "text/html", html);

        //when
        mockMvc.perform(multipart("/eventManagement/webViewArea")
                .file(firstFile)
                .param("eventIdx",saveEvent.getIdx().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @WithMockCustomUser
    void updateEventManagement() throws Exception {
        //given
        MUserInfo writer = mUserInfoDataRepository.findById("forutonatest").get();
        EventManagement event = EventManagement.builder()
                .isOpen(true)
                .eventStartPositionLat(37.00255267215955)
                .eventStarPositionLng(126.18347167968749)
                .eventStartDateTime(LocalDateTime.now())
                .eventEndDateTime(LocalDateTime.now().plusDays(1))
                .detailedDescription("<html><body>test</body></html>")
                .detailAddress("detailAddress")
                .allowComments(true)
                .subTitle("sub Title")
                .title("title")
                .category(EventCategoryType.DEFAULT)
                .writerUid(writer)
                .build();

        EventManagement saveEvent = eventManagementDataRepository.save(event);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        EventManagementUpdateReqDto eventManagementUpdateReqDto = new EventManagementUpdateReqDto();
        eventManagementUpdateReqDto.setIdx(saveEvent.getIdx());
        eventManagementUpdateReqDto.setAllowComments(false);
        eventManagementUpdateReqDto.setCategory(EventCategoryType.DEFAULT);
        eventManagementUpdateReqDto.setDetailedDescription(event.getDetailedDescription());
        eventManagementUpdateReqDto.setEventStartDateTime(LocalDateTime.now());
        eventManagementUpdateReqDto.setEventEndDateTime(LocalDateTime.now().plusDays(2));
        eventManagementUpdateReqDto.setDetailAddress("test DetailAddress");
        eventManagementUpdateReqDto.setEventStartPositionLng(event.getEventStartPositionLng());
        eventManagementUpdateReqDto.setEventStartPositionLat(event.getEventStartPositionLat());
        eventManagementUpdateReqDto.setIsOpen(true);
        eventManagementUpdateReqDto.setSubTitle("change SubText");
        eventManagementUpdateReqDto.setTitle("change Title");

        //when
        mockMvc.perform(put("/eventManagement")
                .content(objectMapper.writeValueAsString(eventManagementUpdateReqDto))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
                .andDo(document("EventManagement", relaxedRequestFields(
                        fieldWithPath("idx").description("이벤트 문서 번호"),
                        fieldWithPath("category").description("카테고리"),
                        fieldWithPath("title").description("제목"),
                        fieldWithPath("subTitle").description("부 제목"),
                        fieldWithPath("isOpen").description("공개 여부"),
                        fieldWithPath("allowComments").description("댓글 가능 여부"),
                        fieldWithPath("eventStartDateTime").description("이벤트 시작 시간"),
                        fieldWithPath("eventEndDateTime").description("이벤트 종료 시간"),
                        fieldWithPath("eventStartPositionLat").description("이벤트 시작 위치"),
                        fieldWithPath("eventStartPositionLng").description("이벤트 시작 위치"),
                        fieldWithPath("detailedDescription").description("이벤트 상세 설명(HTML)"),
                        fieldWithPath("detailAddress").description("상세 주소")
                        ),relaxedResponseFields(
                        fieldWithPath("idx").description("event 고유 Id"),
                        fieldWithPath("category").description("카테고리"),
                        fieldWithPath("title").description("제목"),
                        fieldWithPath("subTitle").description("부 제목"),
                        fieldWithPath("isOpen").description("공개 여부"),
                        fieldWithPath("allowComments").description("댓글 가능 여부"),
                        fieldWithPath("eventStartDateTime").description("이벤트 시작 시간"),
                        fieldWithPath("eventEndDateTime").description("이벤트 종료 시간"),
                        fieldWithPath("eventStartPositionLat").description("이벤트 시작 위치"),
                        fieldWithPath("eventStartPositionLng").description("이벤트 시작 위치"),
                        fieldWithPath("listThumbnail").description("listThumbnail Url"),
                        fieldWithPath("detailPageThumbnail").description("detailPageThumbnail Url"),
                        fieldWithPath("detailedDescription").description("상세 설명(HTML)"),
                        fieldWithPath("webViewArea").description("HTML 파일 URL"),
                        fieldWithPath("detailAddress").description("상세 주소"),
                        fieldWithPath("writerUid.userName").description("작성자")))
                );

        //then
    }


    @Test
    @WithMockCustomUser
    void getEventManagementIdx() throws Exception {
        MUserInfo writer = mUserInfoDataRepository.findById("forutonatest").get();
        EventManagement event = EventManagement.builder()
                .isOpen(true)
                .eventStartPositionLat(37.00255267215955)
                .eventStarPositionLng(126.18347167968749)
                .eventStartDateTime(LocalDateTime.now())
                .eventEndDateTime(LocalDateTime.now().plusDays(1))
                .detailedDescription("<html><body>test</body></html>")
                .allowComments(true)
                .detailAddress("detailAddress")
                .subTitle("sub Title")
                .title("title")
                .category(EventCategoryType.DEFAULT)
                .writerUid(writer)
                .build();

        EventManagement saveEvent = eventManagementDataRepository.save(event);

        //when
        mockMvc.perform(get("/eventManagement/idx")
                .param("idx",saveEvent.getIdx().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("EventManagement", relaxedRequestParameters(
                        parameterWithName("idx").description("event 고유 Id")
                        ),relaxedResponseFields(
                        fieldWithPath("idx").description("event 고유 Id"),
                        fieldWithPath("category").description("카테고리"),
                        fieldWithPath("title").description("제목"),
                        fieldWithPath("subTitle").description("부 제목"),
                        fieldWithPath("isOpen").description("공개 여부"),
                        fieldWithPath("allowComments").description("댓글 가능 여부"),
                        fieldWithPath("eventStartDateTime").description("이벤트 시작 시간"),
                        fieldWithPath("eventEndDateTime").description("이벤트 종료 시간"),
                        fieldWithPath("eventStartPositionLat").description("이벤트 시작 위치"),
                        fieldWithPath("eventStartPositionLng").description("이벤트 시작 위치"),
                        fieldWithPath("listThumbnail").description("listThumbnail Url"),
                        fieldWithPath("detailPageThumbnail").description("detailPageThumbnail Url"),
                        fieldWithPath("detailedDescription").description("상세 설명(HTML)"),
                        fieldWithPath("webViewArea").description("HTML 파일 URL"),
                        fieldWithPath("detailAddress").description("상세 주소"),
                        fieldWithPath("writerUid.userName").description("작성자")))
                );

        //then
    }
}