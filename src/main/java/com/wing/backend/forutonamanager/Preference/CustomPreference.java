package com.wing.backend.forutonamanager.Preference;

import org.springframework.stereotype.Component;

import java.util.Properties;


public interface CustomPreference {
    String loginSuccessRedirectUrl();
    String logoutSuccessRedirectUrl();
    String oauth2RedirectUri();
    String oauth2authorizationUri();
    String oauth2tokenUri();
    String oauth2userInfoUri();
    String oauth2clientName();
    String oauth2registrationId();
    String oauth2clientId();
    String oauth2clientSecret();
    String getManagerDataSourceUserName();
    String getManagerDataSourcePassword();
    String getManagerDataSourceUrl();
    Properties getManagerJpaProperties();
    String getForutonaAppDataSourceUserName();
    String getForutonaAppDataSourcePassword();
    String getForutonaAppDataSourceUrl();
    Properties getForutonaAppJpaProperties();
}
