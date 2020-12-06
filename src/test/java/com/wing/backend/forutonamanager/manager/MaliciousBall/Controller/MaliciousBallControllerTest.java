package com.wing.backend.forutonamanager.manager.MaliciousBall.Controller;

import com.wing.backend.forutonamanager.SecurityTestUtil.TestBase;
import com.wing.backend.forutonamanager.SecurityTestUtil.WithMockCustomUser;
import com.wing.backend.forutonamanager.manager.MaliciousBall.Domain.MaliciousSearchType;
import com.wing.backend.forutonamanager.manager.Malicious.Domain.MaliciousJudgementType;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.relaxedRequestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class MaliciousBallControllerTest extends TestBase {

    @Test
    @WithMockCustomUser
    void getPage() throws Exception {
        //given
        //테스트 데이터 DB에 넣어 두고 테스트 필요.
        //필요시 Service Instance Mock으로 만들어 테스트 해야함.
        //when

        //then
        mockMvc.perform(
                get("/maliciousBall")
                        .param("searchType",
                                String.valueOf(MaliciousSearchType.BEFORE_JUDGMENT))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andDo(document(
                        "MaliciousBall",
                        relaxedRequestParameters(
                                parameterWithName("searchType").description("search Type")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("content[].idx").description("신고 번호"),
                                fieldWithPath("content[].ballUuid").description("신고 받은 BallUuid"),
                                fieldWithPath("content[].ballName").description("ball 이름"),
                                fieldWithPath("content[].makerNickName").description("ball 생성자 닉네임"),
                                fieldWithPath("content[].totalNumberReports").description("총 신고 횟수"),
                                fieldWithPath("content[].crime").description("범죄 또는 이를 조장"),
                                fieldWithPath("content[].abuse").description("욕설, 차별, 사칭 등 타인에 대한 위협 및 피해"),
                                fieldWithPath("content[].privacy").description("사생활 침해, 개인정보 노출"),
                                fieldWithPath("content[].sexual").description("성적인 메시지"),
                                fieldWithPath("content[].advertising").description("광고성 메시지"),
                                fieldWithPath("content[].etc").description("기타"),
                                fieldWithPath("content[].maliciousContentFlag").description("악성 컨텐츠 판단"),
                                fieldWithPath("content[].falseReportFlag").description("허위 신고 판단"),
                                fieldWithPath("content[].judgmentTime").description("판정 시간"),
                                fieldWithPath("content[].judgmentUid").description("판정자")
                        ))
                );
    }

    @Test
    @WithMockCustomUser
    void getMaliciousBall() throws Exception {
        //given
        //테스트 데이터 DB에 넣어 두고 테스트 필요.
        //필요시 Service Instance Mock으로 만들어 테스트 해야함.

        //when

        //then
        mockMvc.perform(
                get("/maliciousBall/idx")
                .param("idx","1"))
                .andDo(print())
                .andDo(document("maliciousBall",
                        relaxedRequestParameters(
                                parameterWithName("idx").description("고유 번호")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("idx").description("고유 번호"),
                                fieldWithPath("ballUuid").description("신고 받은 BallUuid"),
                                fieldWithPath("ballName").description("ball 이름"),
                                fieldWithPath("makerNickName").description("ball 생성자 닉네임"),
                                fieldWithPath("totalNumberReports").description("총 신고 횟수"),
                                fieldWithPath("crime").description("범죄 또는 이를 조장"),
                                fieldWithPath("abuse").description("욕설, 차별, 사칭 등 타인에 대한 위협 및 피해"),
                                fieldWithPath("privacy").description("사생활 침해, 개인정보 노출"),
                                fieldWithPath("sexual").description("성적인 메시지"),
                                fieldWithPath("advertising").description("광고성 메시지"),
                                fieldWithPath("etc").description("기타"),
                                fieldWithPath("maliciousContentFlag").description("악성 컨텐츠 판단"),
                                fieldWithPath("falseReportFlag").description("허위 신고 판단"),
                                fieldWithPath("judgmentTime").description("판정 시간"),
                                fieldWithPath("judgmentUid").description("판정자")
                        ))
                );
    }

    @Test
    @WithMockCustomUser
    void updateJudgement() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(put("/maliciousBall")
                .param("idx","1")
                .param("maliciousJudgementType", String.valueOf(MaliciousJudgementType.MaliciousContent))
        ).andDo(print())
        .andDo(
                document("maliciousBall",
                        relaxedRequestParameters(
                                parameterWithName("idx").description("고유 번호"),
                                parameterWithName("maliciousJudgementType").description("판단 기준")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("idx").description("고유 번호"),
                                fieldWithPath("ballUuid").description("신고 받은 BallUuid"),
                                fieldWithPath("ballName").description("ball 이름"),
                                fieldWithPath("makerNickName").description("ball 생성자 닉네임"),
                                fieldWithPath("totalNumberReports").description("총 신고 횟수"),
                                fieldWithPath("crime").description("범죄 또는 이를 조장"),
                                fieldWithPath("abuse").description("욕설, 차별, 사칭 등 타인에 대한 위협 및 피해"),
                                fieldWithPath("privacy").description("사생활 침해, 개인정보 노출"),
                                fieldWithPath("sexual").description("성적인 메시지"),
                                fieldWithPath("advertising").description("광고성 메시지"),
                                fieldWithPath("etc").description("기타"),
                                fieldWithPath("maliciousContentFlag").description("악성 컨텐츠 판단"),
                                fieldWithPath("falseReportFlag").description("허위 신고 판단"),
                                fieldWithPath("judgmentTime").description("판정 시간"),
                                fieldWithPath("judgmentUid").description("판정자")
                        )
                )
        );

    }
}