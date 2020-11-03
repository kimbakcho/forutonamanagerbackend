package com.wing.backend.forutonamanager.manager.TermsConditions.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wing.backend.forutonamanager.RestDoc.RestDocsConfiguration;
import com.wing.backend.forutonamanager.SecurityTestUtil.WithMockCustomUser;
import com.wing.backend.forutonamanager.manager.MUserInfo.Domain.MUserInfo;
import com.wing.backend.forutonamanager.manager.MUserInfo.Repository.MUserInfoDataRepository;
import com.wing.backend.forutonamanager.manager.TermsConditions.Domain.TermsConditions;
import com.wing.backend.forutonamanager.manager.TermsConditions.Dto.TermsConditionsSaveDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Dto.TermsConditionsUpdateDto;
import com.wing.backend.forutonamanager.manager.TermsConditions.Repository.TermsConditionsDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.relaxedRequestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@Transactional
class TermsConditionsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TermsConditionsDataRepository termsConditionsDataRepository;

    @Autowired
    MUserInfoDataRepository mUserInfoDataRepository;


    @Test
    @WithMockCustomUser
    void saveTermsConditions() throws Exception {

        TermsConditionsSaveDto termsConditionsSaveDto = new TermsConditionsSaveDto();
        termsConditionsSaveDto.setTermsName("서비스 이용 약관");

        termsConditionsSaveDto.setTermsContent("TEST");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(
                post("/termsConditions/Terms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(termsConditionsSaveDto))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("TermsConditionsAPI", relaxedRequestFields(
                                fieldWithPath("termsName").description("정책 이름"),
                                fieldWithPath("termsContent").description("정책 내용(HTML)")
                                ),
                                relaxedResponseFields(
                                        fieldWithPath("idx").description("doc Number"),
                                        fieldWithPath("termsName").description("문서 이름"),
                                        fieldWithPath("termsContent").description("문서 내용")
                                )
                        )
                );
    }


    @Test
    @WithMockCustomUser(uid = "forutonatest")
    void updateTermsConditions() throws Exception {

        MUserInfo originSaveUser = mUserInfoDataRepository.findById("forutonawing").get();

        TermsConditions reqTestDoc = TermsConditions.builder().termsContent("TEST").modifyUser(originSaveUser)
                .modifyDate(LocalDateTime.now())
                .termsName("서비스 약관 1")
                .build();

        TermsConditions testDoc = termsConditionsDataRepository.save(reqTestDoc);

        TermsConditionsUpdateDto termsConditionsUpdateDto = new TermsConditionsUpdateDto();

        termsConditionsUpdateDto.setIdx(testDoc.getIdx());
        termsConditionsUpdateDto.setTermsContent("TEST1");
        termsConditionsUpdateDto.setTermsName("서비스 약관 2");

        ObjectMapper objectMapper = new ObjectMapper();


        mockMvc.perform(
                put("/termsConditions/Terms")
                        .content(objectMapper.writeValueAsString(termsConditionsUpdateDto))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(print())
                .andDo(document("TermsConditionsAPI", relaxedRequestFields(
                        fieldWithPath("idx").description("문서 번호"),
                        fieldWithPath("termsName").description("정책 이름"),
                        fieldWithPath("termsContent").description("정책 내용(HTML)")
                ), relaxedResponseFields(
                        fieldWithPath("idx").description("doc Number"),
                        fieldWithPath("termsName").description("수정된 이름"),
                        fieldWithPath("termsContent").description("수정된 내용")
                )));
    }

    @Test
    @WithMockCustomUser()
    void deleteTermsConditions() throws Exception {
        MUserInfo originSaveUser = mUserInfoDataRepository.findById("forutonawing").get();

        TermsConditions reqTestDoc = TermsConditions.builder().termsContent("TEST").modifyUser(originSaveUser)
                .modifyDate(LocalDateTime.now())
                .termsName("서비스 약관 1")
                .build();

        TermsConditions testDoc = termsConditionsDataRepository.save(reqTestDoc);

        mockMvc.perform(delete("/termsConditions/Terms")
                .param("idx", testDoc.getIdx().toString())
        ).andDo(print())
                .andExpect(status().isOk())
                .andDo(document("TermsConditionsAPI", relaxedRequestParameters(
                        parameterWithName("idx").description("삭제할 문서 번호"))
                ));
    }


    @Test
    void getTermsConditions() throws Exception {
        MUserInfo originSaveUser = mUserInfoDataRepository.findById("forutonawing").get();

        TermsConditions reqTestDoc = TermsConditions.builder().termsContent("TEST").modifyUser(originSaveUser)
                .modifyDate(LocalDateTime.now())
                .termsName("서비스 약관 1")
                .build();

        TermsConditions testDoc = termsConditionsDataRepository.save(reqTestDoc);

        mockMvc.perform(get("/termsConditions/Terms")
                .param("idx", testDoc.getIdx().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("TermsConditionsAPI",
                        relaxedRequestParameters(
                                parameterWithName("idx").description("조회 할 정책 문서 번호")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("idx").description("doc Number"),
                                fieldWithPath("termsName").description("수정된 이름"),
                                fieldWithPath("termsContent").description("수정된 내용")
                        )
                ));
    }
}