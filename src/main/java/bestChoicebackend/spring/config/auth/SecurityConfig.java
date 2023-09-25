package bestChoicebackend.spring.config.auth;

import bestChoicebackend.spring.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;

import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig{
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2AccessTokenResponseClient customOAuth2AccessTokenResponseClient;
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
//                .oauth2Client(oauth2 -> oauth2
//                        .authorizationCodeGrant(codeGrant -> codeGrant
//                                .accessTokenResponseClient(customOAuth2AccessTokenResponseClient)))
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/loginneed")
                        .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig
                                .baseUri("/oauth2/code/*"))
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
//                        .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig
//                                .baseUri("/"))
                        .defaultSuccessUrl("https://next-bestchoice-project.vercel.app/"));
        return http.build();
    }



}
