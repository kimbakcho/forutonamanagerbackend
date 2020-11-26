package com.wing.backend.forutonamanager.Preference;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Profile("real")
public class realPreference implements CustomPreference {
    @Override
    public String loginSuccessRedirectUrl() {
        return "https://forutona.thkomeet.com:8443/forutonamanagerment";
    }

    @Override
    public String logoutSuccessRedirectUrl() {
        return "https://forutona.thkomeet.com:8443/forutonamanagerment/";
    }

    @Override
    public String oauth2RedirectUri() {
        return "https://forutona.thkomeet.com:8443/bforutonamanagerment/login/oauth2/code/forutonaMain";
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

    @Override
    public String getManagerDataSourceUserName() {
        return "neoforutonatester";
    }

    @Override
    public String getManagerDataSourcePassword() {
        return "forutona1020";
    }

    @Override
    public String getManagerDataSourceUrl() {
        return "jdbc:mysql://forutonadb.thkomeet.com:3306/forutonamanager_test?serverTimezone=Asia/Seoul&useSSL=yes&verifyServerCertificate=false";
    }

    @Override
    public Properties getManagerJpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "com.wing.backend.forutonamanager.CustomDialect");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.default_batch_fetch_size", "1000");
        jpaProperties.put("hibernate.use_sql_comment", "true");
        return jpaProperties;
    }

    @Override
    public String getForutonaAppDataSourceUserName() {
        return "managementforutona";
    }

    @Override
    public String getForutonaAppDataSourcePassword() {
        return "forutona1020";
    }

    @Override
    public String getForutonaAppDataSourceUrl() {
        return "jdbc:mysql://forutonadb.thkomeet.com:3306/forutona_test?serverTimezone=Asia/Seoul&useSSL=yes&verifyServerCertificate=false";
    }

    @Override
    public Properties getForutonaAppJpaProperties() {
        return getManagerJpaProperties();
    }
}
