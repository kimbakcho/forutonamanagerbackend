package com.wing.backend.forutonamanager.Preference;

import org.springframework.stereotype.Component;


public interface CustomPreference {
    String loginSuccessRedirectUrl();
    String logoutSuccessRedirectUrl();
}
