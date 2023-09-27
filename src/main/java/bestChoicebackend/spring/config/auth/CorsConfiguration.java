package bestChoicebackend.spring.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .exposedHeaders("Set-Cookie")
                        .allowedOrigins("https://epicktrees.net","https://localhost:3000","http://localhost:8080")
                        .allowCredentials(true)
                        .allowedHeaders("JSESSIONID","Cookie")
                        .allowedMethods("GET","POST");

            }
        };
    }

}
