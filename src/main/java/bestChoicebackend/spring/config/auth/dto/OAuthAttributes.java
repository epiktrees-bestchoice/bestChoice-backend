package bestChoicebackend.spring.config.auth.dto;

import bestChoicebackend.spring.domain.Role;
import bestChoicebackend.spring.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder // 빌더 자동 생성
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String userEmail;
    private String picture;

//    public OAuthAttributes(Map<String, Object> attributes,
//                           String nameAttributeKey, String name,
//                           String userEmail, String picture) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.userEmail = userEmail;
//        this.picture = picture;
//    }

    /* of()
     * OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나 변환
     */
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (registrationId.equals("kakao")){
            System.out.println("--user infos--");
            Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
            Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
            System.out.println((String) kakaoProfile.get("nickname"));
            System.out.println((String) kakaoAccount.get("email"));

            return OAuthAttributes.builder()
                    .name((String) kakaoProfile.get("nickname"))
                    .userEmail((String) kakaoAccount.get("email"))
                    .picture((String) kakaoProfile.get("profile_image_url"))
                    .attributes(attributes)
                    .nameAttributeKey(userNameAttributeName)
                    .build();
        }
        else {
            return OAuthAttributes.builder()
                    .name((String) attributes.get("name"))
                    .userEmail((String) attributes.get("email"))
                    .picture((String) attributes.get("picture"))
                    .attributes(attributes)
                    .nameAttributeKey(userNameAttributeName)
                    .build();
        }
    }

    /* toEntity()
     * User 엔티티 생성
     * OAuthAttributes에서 엔티티 생성 시점 = 처음 가입 시
     * OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스 생성
     */
    public User toEntity() {
        return User.builder()
                .name(name)
                .userEmail(userEmail)
                .picture(picture)
                .role(Role.GUEST)	// 가입 기본 권한 == GUEST
                .build();
    }
}
