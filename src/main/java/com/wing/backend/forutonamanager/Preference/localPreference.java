package com.wing.backend.forutonamanager.Preference;


import com.wing.backend.forutonamanager.Preference.CustomPreference;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class localPreference implements CustomPreference {
    @Override
    public String loginSuccessRedirectUrl() {
        return "https://localhost:8080";
    }

    @Override
    public String logoutSuccessRedirectUrl() {
        return "https://localhost:8080";
    }

    @Override
    public String oauth2RedirectUri() {
        return "https://localhost:8443/bforutonamanagermenttest/login/oauth2/code/TestAuth";
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
        return "TestAuth";
    }

    @Override
    public String oauth2registrationId() {
        return "TestAuth";
    }

    @Override
    public String oauth2clientId() {
        return "TestAuth";
    }

    @Override
    public String oauth2clientSecret() {
        return "TestAuth";
    }


}
