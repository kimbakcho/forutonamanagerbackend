package com.wing.backend.forutonamanager.Preference;

import org.springframework.stereotype.Component;


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
}
