package com.wing.backend.forutonamanager.forutona.FUserInfo.Controller;

import com.wing.backend.forutonamanager.SecurityTestUtil.TestBase;
import com.wing.backend.forutonamanager.SecurityTestUtil.WithMockCustomUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class FUserInfoControllerTest extends TestBase {

    @Test
    @WithMockCustomUser
    void getFUserInfoResDto() throws Exception {

        //given
        //TEST 에 필요한 데이터는 TEST DB 에 넣어둠

        //then
        mockMvc.perform(
                get("/fUserInfo/uid")
                .param("uid","qQBi3epcfYQSpLbnm86duPstPYd2")
        ).andDo(print());
    }
}