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

    final TokenService tokenService;

    //DefaultAuthorizationCodeTokenResponseClient 참조
    @GetMapping(value = "/token/auth")
    OAuth2AccessTokenResponse authToken(@RequestParam String code, HttpServletResponse response) {
        return tokenService.authToken(code,response);
    }

    @PostMapping(value = "/token/reFreshToken")
    OAuth2AccessTokenResponse reFreshToken(String refreshToken, HttpServletResponse response) {
        return tokenService.reFreshToken(refreshToken,response);
    }


    @GetMapping(value = "/TEST1")
    public String Test1(@AuthenticationPrincipal Object client){
        return "TEST1";
    }


}
