package com.wing.backend.forutonamanager.manager.Security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2SPAFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        try{
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception exception){
            if (exception instanceof AuthenticationException) {
                throw exception;
            } else if (exception instanceof AccessDeniedException) {
                AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/SPALogin");
                if(antPathRequestMatcher.matches(request)){
                    throw exception;
                }else {
                    response.sendError(401,"AccessDeniedException");
                }
            }else {
                throw exception;
            }
        }
    }
}
