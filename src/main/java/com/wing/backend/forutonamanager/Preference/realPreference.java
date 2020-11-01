package com.wing.backend.forutonamanager.Preference;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("real")
public class realPreference implements CustomPreference{
    @Override
    public String loginSuccessRedirectUrl() {
        return "https://forutona.thkomeet.com:8443/forutonamanagement";
    }

    @Override
    public String logoutSuccessRedirectUrl() {
        return "https://forutona.thkomeet.com:8443/forutonamanagement/";
    }
}
