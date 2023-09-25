package bestChoicebackend.spring.config.auth;

import bestChoicebackend.spring.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig{
    private final CustomOAuth2UserService customOAuth2UserService;
    private CustomOAuth2AccessTokenResponseClient customOAuth2AccessTokenResponseClient;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login","/try/**").permitAll()
//                        .requestMatchers("/","css/**","/images/**","/js/**","/h2-console/**","/profile").permitAll()
//                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/product/**").permitAll()
                        .requestMatchers("/oauth2/authorization/**").permitAll()
                        .requestMatchers("/api/v1/**").permitAll()
                        .anyRequest().authenticated())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")  )
                .oauth2Client(oauth2 -> oauth2
                        .authorizationCodeGrant(codeGrant -> codeGrant
                                .accessTokenResponseClient(customOAuth2AccessTokenResponseClient)))
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login")
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
//                        .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig
//                                .baseUri("/"))
                        .defaultSuccessUrl("http://localhost:3000/"));
        return http.build();
    }

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

}
