package bestChoicebackend.spring.config.auth.dto;

import bestChoicebackend.spring.domain.Role;
import bestChoicebackend.spring.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String userEmail;
    private String picture;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .userEmail((String) attributes.get("userEmail"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .userEmail(userEmail)
                .picture(picture)
                .role(Role.GUEST)	// 가입 기본 권한 == GUEST
                .build();
    }
}
