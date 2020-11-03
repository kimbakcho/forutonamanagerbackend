package com.wing.backend.forutonamanager.Preference;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("real")
public class realPreference implements CustomPreference {
    @Override
    public String loginSuccessRedirectUrl() {
        return "https://forutona.thkomeet.com:8443/forutonamanagement";
    }

    @Override
    public String logoutSuccessRedirectUrl() {
        return "https://forutona.thkomeet.com:8443/forutonamanagement/";
    }

    @Override
    public String oauth2RedirectUri() {
        return "https://forutona.thkomeet.com:8443/forutonamanagermentb/login/oauth2/code/forutonaMain";
    }

    @Override
    public String oauth2authorizationUri() {
        return "https://forutona.thkomeet.com:8443/mAuth/oauth/authorize";
    }

    @Override
    public String oauth2tokenUri() {
        return "https://forutona.thkomeet.com:8443/mAuth/oauth/token";
    }

    @Override
    public String oauth2userInfoUri() {
        return "https://forutona.thkomeet.com:8443/mAuth/MUserInfo/Me";
    }

    @Override
    public String oauth2clientName() {
        return "forutonaMain";
    }

    @Override
    public String oauth2registrationId() {
        return "forutonaMain";
    }

    @Override
    public String oauth2clientId() {
        return "forutonaMain";
    }

    @Override
    public String oauth2clientSecret() {
        return "forutona1020";
    }
}
