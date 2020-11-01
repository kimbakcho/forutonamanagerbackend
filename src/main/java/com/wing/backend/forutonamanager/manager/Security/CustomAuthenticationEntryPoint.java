package com.wing.backend.forutonamanager.manager.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint, ApplicationContextAware {

    final ConfigurationBeanFactoryMetadata configurationBeanFactoryMetadata;
    ApplicationContext applicationContext;

    public void printBeans() {
        Object bean = applicationContext.getBean("org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint");
        System.out.println(bean.hashCode());
    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        printBeans();


//        if(request.getRequestURI().equals("/SPALogin")){
//            bean.commence(request,response,authException);
//            return ;
//        }
        if(authException.getClass().isAssignableFrom(InsufficientAuthenticationException.class)){
            System.out.println(authException.getMessage());
            response.sendError(401,"InsufficientAuthenticationException");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
        this.applicationContext = applicationContext;
    }
}
