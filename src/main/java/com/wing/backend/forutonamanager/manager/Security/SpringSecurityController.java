package com.wing.backend.forutonamanager.manager.Security;

import com.wing.backend.forutonamanager.Preference.CustomPreference;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Request;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class SpringSecurityController {

    final CustomPreference customPreference;

    @GetMapping("/SPALogin")
    public void SPALogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();
        defaultRedirectStrategy.sendRedirect(request,response,customPreference.loginSuccessRedirectUrl());
    }
}
