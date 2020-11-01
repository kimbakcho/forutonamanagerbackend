package com.wing.backend.forutonamanager.manager.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class TokenController {

    @GetMapping(value = "/TEST1")
    public String Test1(@AuthenticationPrincipal Object client){

        return "TEST1";
    }

    @GetMapping(value = "/redirect")
    public String re(){
        return "Rereict";
    }


}
