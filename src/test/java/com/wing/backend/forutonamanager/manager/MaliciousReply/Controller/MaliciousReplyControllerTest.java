package com.wing.backend.forutonamanager.manager.MaliciousReply.Controller;

import com.wing.backend.forutonamanager.SecurityTestUtil.TestBase;
import com.wing.backend.forutonamanager.SecurityTestUtil.WithMockCustomUser;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousSearchType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.relaxedRequestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class MaliciousReplyControllerTest extends TestBase {

    @Test
    @WithMockCustomUser
    void getPage() throws Exception {
        //given
        //테스트 데이터 DB에 넣어 두고 테스트 필요.
        //필요시 Service Instance Mock으로 만들어 테스트 해야함.
        //when

        //then
        mockMvc.perform(
                get("/maliciousReply")
                        .param("searchType", String.valueOf(MaliciousSearchType.BEFORE_JUDGMENT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document("MaliciousReply",
                        relaxedRequestParameters(
                                parameterWithName("searchType").description("search Type")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("content[].idx").description("신고 번호"),
                                fieldWithPath("content[].replyUuid").description("댓글 Uuid"),
                                fieldWithPath("content[].replyText").description("댓글 내용"),
                                fieldWithPath("content[].replyUserNickName").description("댓글 작성자"),
                                fieldWithPath("content[].totalNumberReports").description("총 신고된 횟수"),
                                fieldWithPath("content[].falseReportFlag").description("falseReportFlag"),
                                fieldWithPath("content[].maliciousContentFlag").description("maliciousContentFlag")
                        ))
        );
    }

    @Test
    @WithMockCustomUser
    void getMaliciousReply() throws Exception {
        //given
        //테스트 데이터 DB에 넣어 두고 테스트 필요.
        //필요시 Service Instance Mock으로 만들어 테스트 해야함.
        //when

        //then
        mockMvc.perform(
                get("/maliciousReply/idx")
                    .param("idx","1"))
            .andDo(print())
            .andDo(document("MaliciousReply",
                    relaxedRequestParameters(
                            parameterWithName("idx").description("신고 번호")
                    ),
                    relaxedResponseFields(
                            fieldWithPath("idx").description("신고 번호"),
                            fieldWithPath("replyUuid").description("댓글 Uuid"),
                            fieldWithPath("totalNumberReports").description("총 신고 횟수"),
                            fieldWithPath("crime").description("범죄 또는 이를 조장"),
                            fieldWithPath("abuse").description("욕설, 차별, 사칭 등 타인에 대한 위협 및 피해"),
                            fieldWithPath("privacy").description("사생활 침해, 개인정보 노출"),
                            fieldWithPath("sexual").description("성적인 메시지"),
                            fieldWithPath("advertising").description("광고성 메시지"),
                            fieldWithPath("etc").description("기타"),
                            fieldWithPath("maliciousContentFlag").description("악성 컨텐츠 판단"),
                            fieldWithPath("falseReportFlag").description("허위 신고 판단")
                            )));
    }

}