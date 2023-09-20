package bestChoicebackend.spring.config.auth.dto;

import bestChoicebackend.spring.domain.Role;
import bestChoicebackend.spring.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder // 빌더 자동 생성
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String userEmail;
    private String picture;
    private String social;
    private String nickName;


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

            Map<String, Object> commonAttributes =new HashMap<String,Object>();
            commonAttributes.put(userNameAttributeName, attributes.get(userNameAttributeName));
            commonAttributes.put("name",(String) kakaoProfile.get("nickname"));
            commonAttributes.put("nickname", (String) kakaoProfile.get("nickname"));
            commonAttributes.put("userEmail", (String) kakaoAccount.get("email"));
            commonAttributes.put("picture", (String) kakaoProfile.get("profile_image_url"));
            commonAttributes.put("social",registrationId);

            return OAuthAttributes.builder()
                    .name((String) kakaoProfile.get("nickname"))
                    .nickName((String) kakaoProfile.get("nickname"))
                    .userEmail((String) kakaoAccount.get("email"))
                    .picture((String) kakaoProfile.get("profile_image_url"))
                    .social(registrationId)
                    .attributes(commonAttributes)
                    .nameAttributeKey(userNameAttributeName)
                    .build();

        } else if (registrationId.equals("facebook")) {

            System.out.println("--user infos--");
            System.out.println((String) attributes.get("name"));
            System.out.println((String) attributes.get("email"));

            Map<String, Object> commonAttributes =new HashMap<String,Object>();
            commonAttributes.put(userNameAttributeName, attributes.get(userNameAttributeName));
            commonAttributes.put("name", attributes.get("name"));
            commonAttributes.put("nickname", attributes.get("name"));
            commonAttributes.put("userEmail", attributes.get("email"));
            commonAttributes.put("picture", attributes.get("profile_image_url"));
            commonAttributes.put("social",registrationId);

            return OAuthAttributes.builder()
                    .name((String) attributes.get("name"))
                    .nickName((String) attributes.get("name"))
                    .userEmail((String) attributes.get("email"))
                    .social(registrationId)
                    .attributes(commonAttributes)
                    .nameAttributeKey(userNameAttributeName)
                    .build();
        }
        else { // google

            Map<String, Object> commonAttributes =new HashMap<String,Object>();
            commonAttributes.put(userNameAttributeName, attributes.get(userNameAttributeName));
            commonAttributes.put("name", attributes.get("name"));
            commonAttributes.put("nickname", attributes.get("name"));
            commonAttributes.put("userEmail", attributes.get("email"));
            commonAttributes.put("picture", attributes.get("picture"));
            commonAttributes.put("social",registrationId);

            return OAuthAttributes.builder()
                    .name((String) attributes.get("name"))
                    .nickName((String) attributes.get("name"))
                    .userEmail((String) attributes.get("email"))
                    .picture((String) attributes.get("picture"))
                    .social(registrationId)
                    .attributes(commonAttributes)
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
                .nickName(nickName)
                .userEmail(userEmail)
                .social(social)
                .picture(picture)
                .role(Role.USER)	// 가입 기본 권한 == USER
                .build();
    }
}
