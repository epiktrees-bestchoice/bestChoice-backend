package bestChoicebackend.spring.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2AccessTokenResponseClient implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
    @Override
    public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        ClientRegistration clientRegistration = authorizationGrantRequest.getClientRegistration();
        System.out.println("--요청 정보 -- clientName, registrationId, ProviderDetails");
        System.out.println(clientRegistration.getClientName());
        System.out.println(clientRegistration.getRegistrationId());
        System.out.println(clientRegistration.getProviderDetails());

        System.out.println("---인증 토큰 받는 로직 시작---");
        DefaultAuthorizationCodeTokenResponseClient defaultAuthCodeToken = new DefaultAuthorizationCodeTokenResponseClient();
        OAuth2AccessTokenResponse response = defaultAuthCodeToken.getTokenResponse(authorizationGrantRequest);

        String accessToken = response.getAccessToken().getTokenValue();
        System.out.println(accessToken);

        return response;
    }
}
