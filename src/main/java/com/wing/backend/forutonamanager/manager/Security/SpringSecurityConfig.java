//package com.wing.backend.forutonamanager.manager.Security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.web.access.ExceptionTranslationFilter;
//
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    final static String SecretKey = "Zm9ydXRvbmF3aW5nIWZvcnV0b25hd2luZw==";
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .csrf().disable();
//
//        http
//                .oauth2Login();
//
//        http.authorizeRequests()
//                .anyRequest().authenticated();
//
//    }
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(this.TestAuthClientRegistration());
//    }
//
//    private ClientRegistration TestAuthClientRegistration() {
//
//        return ClientRegistration.withRegistrationId("TestAuth")
//                .clientId("TestAuth")
//                .clientSecret("123456")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUriTemplate("https://localhost:8443/login/oauth2/code/TestAuth")
//                .scope("message.read", "message.write")
//                .authorizationUri("https://thkomeet.com:8443/mAuth/oauth/authorize")
//                .tokenUri("https://thkomeet.com:8443/mAuth/oauth/token")
//                .userInfoUri("https://thkomeet.com:8443/mAuth/MUserInfo/Me")
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .clientName("TestAuth")
//                .registrationId("TestAuth")
//                .build();
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Bean
//    public JwtDecoder customDecoder() {
//        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(new SecretKeySpec(SecretKey.getBytes(), "HMACSHA256"))
//                .macAlgorithm(MacAlgorithm.HS256)
//                .build();
//        return decoder;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return delegatingPasswordEncoder;
//    }
//}
