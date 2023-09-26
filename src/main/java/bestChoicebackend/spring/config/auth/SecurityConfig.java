package bestChoicebackend.spring.config.auth;

import bestChoicebackend.spring.domain.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

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
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins((Arrays.asList("http://localhost:3000","https://epicktrees.net")));
                        config.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin",
                                "Access-Control-Allow-Headers" ,
                                "Access-Control-Allow-Credentials"));
                        config.setAllowCredentials(true);
                        System.out.println(config.getAllowedOrigins());
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
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
                        .loginPage("/login")
                        .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig
                                .baseUri("/oauth2/code/*"))
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
//                        .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig
//                                .baseUri("/"))
                        .defaultSuccessUrl("https://epicktrees.net/"));
        return http.build();
    }



}
