package com.wing.backend.forutonamanager.manager.Notice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wing.backend.forutonamanager.SecurityTestUtil.TestBase;
import com.wing.backend.forutonamanager.SecurityTestUtil.WithMockCustomUser;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.Notice.Domain.Notice;
import com.wing.backend.forutonamanager.manager.Notice.Dto.InsertNoticeReqDto;
import com.wing.backend.forutonamanager.manager.Notice.Dto.UpdateNoticeReqDto;
import com.wing.backend.forutonamanager.manager.Notice.Repository.NoticeDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.relaxedRequestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NoticeControllerTest extends TestBase {

    @Autowired
    NoticeDataRepository noticeDataRepository;

    @Autowired
    MUserInfoDataRepository mUserInfoDataRepository;

    @Test
    @WithMockCustomUser
    void getNotices() throws Exception {

        MUserInfo mUserInfo = mUserInfoDataRepository.findById("forutonawing").get();
        Notice notice  = Notice.builder()
                .title("TEST 제목")
                .writerUid(mUserInfo)
                .openFlag(true)
                .content("TEST")
                .modifyDate(LocalDateTime.now())
                .build();

        noticeDataRepository.save(notice);

        mockMvc.perform(get("/notice")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "modifyDate,desc"))
                .andDo(print())
                .andDo(
                        document("NoticeControllerTest", relaxedRequestParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지사이즈"),
                                parameterWithName("sort").description("정렬")
                                )
                        )
                );
    }

    @Test
    @WithMockCustomUser
    void insertNotice() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        InsertNoticeReqDto insertNoticeReqDto = new InsertNoticeReqDto();
        insertNoticeReqDto.setTitle("TEST Title");
        insertNoticeReqDto.setContent("TEST Content");
        insertNoticeReqDto.setOpenFlag(true);

        mockMvc.perform(post("/notice")
                .content(objectMapper.writeValueAsString(insertNoticeReqDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
        .andDo(document("NoticeControllerTest", relaxedRequestFields(
                    fieldWithPath("title").description("제목"),
                    fieldWithPath("content").description("내용"),
                    fieldWithPath("openFlag").description("공개 여부")),
                    relaxedResponseFields(
                            fieldWithPath("idx").description("문서 Id"),
                            fieldWithPath("title").description("제목"),
                            fieldWithPath("content").description("내용"),
                            fieldWithPath("openFlag").description("공개 여부"),
                            fieldWithPath("modifyDate").description("수정 or 작성일")
                    )
                )
        );
    }

    @Test
    @WithMockCustomUser
    void updateNotice() throws Exception {

        MUserInfo mUserInfo = mUserInfoDataRepository.findById("forutonawing").get();
        Notice notice  = Notice.builder()
                .title("TEST 제목")
                .writerUid(mUserInfo)
                .openFlag(true)
                .content("TEST")
                .modifyDate(LocalDateTime.now())
                .build();

        Notice saveItem = noticeDataRepository.save(notice);

        ObjectMapper objectMapper = new ObjectMapper();

        UpdateNoticeReqDto updateNoticeReqDto = new UpdateNoticeReqDto();
        updateNoticeReqDto.setIdx(saveItem.getIdx());
        updateNoticeReqDto.setTitle("TEST Title");
        updateNoticeReqDto.setContent("TEST Content");
        updateNoticeReqDto.setOpenFlag(true);

        mockMvc.perform(put("/notice")
                .content(objectMapper.writeValueAsString(updateNoticeReqDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andDo(document("NoticeControllerTest", relaxedRequestFields(
                        fieldWithPath("idx").description("수정할 문서 번호"),
                        fieldWithPath("title").description("제목"),
                        fieldWithPath("content").description("내용"),
                        fieldWithPath("openFlag").description("공개 여부")),
                        relaxedResponseFields(
                                fieldWithPath("idx").description("문서 Id"),
                                fieldWithPath("title").description("제목"),
                                fieldWithPath("content").description("내용"),
                                fieldWithPath("openFlag").description("공개 여부"),
                                fieldWithPath("modifyDate").description("수정 or 작성일")
                        )
                        )
                );
    }

    @Test
    @WithMockCustomUser
    void deleteNotice() throws Exception {
        MUserInfo mUserInfo = mUserInfoDataRepository.findById("forutonawing").get();
        Notice notice  = Notice.builder()
                .title("TEST 제목")
                .writerUid(mUserInfo)
                .openFlag(true)
                .content("TEST")
                .modifyDate(LocalDateTime.now())
                .build();

        Notice saveItem = noticeDataRepository.save(notice);

        mockMvc.perform(delete("/notice")
                .param("idx",saveItem.getIdx().toString()))
                .andDo(print())
                .andDo(document("NoticeControllerTest",
                        relaxedRequestParameters(
                                parameterWithName("idx").description("삭제할 문서 번호")
                            )
                        ))
                .andExpect(status().isOk());
    }
}