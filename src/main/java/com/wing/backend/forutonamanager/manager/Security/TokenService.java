package com.wing.backend.forutonamanager.manager.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponse;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${resource.redirectUrlTemplate}")
    public String redirectUrlTemplate;
    @Qualifier("customDecoder")
    @Autowired
    JwtDecoder jwtDecoder;
    ClientRegistration clientRegistration;
    @Value("${resource.authorizationUrl}")
    private String authorizationUrl;
    @Value("${resource.tokenUrl}")
    private String tokenUrl;
    @Value("${resource.userInfoUrl}")
    private String userInfoUrl;
    @Value("${resource.clientId}")
    private String clientId;
    @Value("${resource.clientSecret}")
    private String clientSecret;

    @PostConstruct
    public void init() {
        clientRegistration = ClientRegistration.withRegistrationId("resource")
                .redirectUriTemplate(redirectUrlTemplate)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizationUri(authorizationUrl)
                .tokenUri(tokenUrl)
                .userInfoUri(userInfoUrl)
                .scope("message.read", "message.write")
                .build();
    }

    public OAuth2AccessTokenResponse authToken(String code, HttpServletResponse response) {
        Converter<OAuth2AuthorizationCodeGrantRequest, RequestEntity<?>> requestEntityConverter = new OAuth2AuthorizationCodeGrantRequestEntityConverter();

        OAuth2AuthorizationRequest tokenReq = OAuth2AuthorizationRequest.authorizationCode()
                .redirectUri(clientRegistration.getRedirectUriTemplate())
                .clientId(clientRegistration.getClientId())
                .scopes(clientRegistration.getScopes())
                .authorizationUri(authorizationUrl)
                .authorizationRequestUri(authorizationUrl)
                .state("1234")
                .build();

        OAuth2AuthorizationResponse tokenRes = OAuth2AuthorizationResponse
                .success(code)
                .code(code)
                .state("1234")
                .redirectUri(clientRegistration.getRedirectUriTemplate()).build();

        OAuth2AuthorizationExchange oAuth2AuthorizationExchange = new OAuth2AuthorizationExchange(tokenReq, tokenRes);

        OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest = new OAuth2AuthorizationCodeGrantRequest(clientRegistration, oAuth2AuthorizationExchange);

        DefaultAuthorizationCodeTokenResponseClient defaultAuthorizationCodeTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        return defaultAuthorizationCodeTokenResponseClient.getTokenResponse(authorizationCodeGrantRequest);


    }

    public OAuth2AccessTokenResponse reFreshToken(String refreshToken, HttpServletResponse response) {
        DefaultRefreshTokenTokenResponseClient responseClient = new DefaultRefreshTokenTokenResponseClient();
        Jwt decode = jwtDecoder.decode(refreshToken);
        Instant exp = decode.getClaim("exp");
        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, refreshToken, null, exp);
        OAuth2RefreshToken oAuth2RefreshToken = new OAuth2RefreshToken(refreshToken, null);

        OAuth2RefreshTokenGrantRequest oAuth2RefreshTokenGrantRequest = new OAuth2RefreshTokenGrantRequest(clientRegistration,
                oAuth2AccessToken,
                oAuth2RefreshToken);
        return responseClient.getTokenResponse(oAuth2RefreshTokenGrantRequest);
    }

}
