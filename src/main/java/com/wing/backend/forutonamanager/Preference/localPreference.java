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
}
