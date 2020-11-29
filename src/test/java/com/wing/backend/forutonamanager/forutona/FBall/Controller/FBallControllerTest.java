package com.wing.backend.forutonamanager.forutona.FBall.Controller;

import com.wing.backend.forutonamanager.SecurityTestUtil.TestBase;
import com.wing.backend.forutonamanager.SecurityTestUtil.WithMockCustomUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FBallControllerTest extends TestBase {

    @Test
    @WithMockCustomUser
    void getFBall() throws Exception {
        //given
        // ballUuid 01889e4c-15d8-4989-2807-871d34f1a8382 을 TESTDB에 미리 만들어 둠
        //when

        //then
        mockMvc.perform(
                get("/FBall/BallUuid")
                .param("ballUuid","01889e4c-15d8-4989-2807-871d34f1a8382"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}