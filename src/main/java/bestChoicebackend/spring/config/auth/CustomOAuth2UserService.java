package bestChoicebackend.spring.config.auth;

import bestChoicebackend.spring.config.auth.dto.OAuthAttributes;
import bestChoicebackend.spring.config.auth.dto.SessionUser;
import bestChoicebackend.spring.domain.User;
import bestChoicebackend.spring.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /* registrationId
         * 현재 로그인 진행 중인 서비스 구분하는 코드.
         * 이후에 여러가지 추가할 때 네이버인지 구글인지 구분
         */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("--registrationId--");
        System.out.println(registrationId);
        /* userNameAttributeName
         * OAuth2 로그인 진행 시 키가 되는 필드값 (=Primary Key)
         * 구글 기본 코드: sub, 네이버 카카오 등은 기본 지원 x
         * 이후 네이버, 구글 로그인 동시 지원시 사용
         */
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        System.out.println("--userName--");
        System.out.println(userNameAttributeName);
        System.out.println("--userRequest--");
        System.out.println(userRequest);
        /* OAuthAttributes
         * OAuth2UserService를 통해 가져온 OAuth2User의 attribute
         * 네이버 등 다른 소셜 로그인도 이 클래스 사용
         */
        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        System.out.println("--attributes--");
        System.out.println(attributes.getAttributes());
        System.out.println("--oAuth2User name--");
        System.out.println(oAuth2User.getName());

        User user = saveOrUpdate(attributes);

        /* SessionUser
         * 세션에 사용자 정보를 저장하기 위한 dto 클래스
         * (User 클래스를 사용하지 않고 새로 만들었다.)
         */
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    // 처음 회원가입하는 유저 정보 저장
    // 유저 정보 업데이트
    // 궁금한 점은 data source를 사용하지 않고 그냥 repository를 사용하네?
    private User saveOrUpdate(OAuthAttributes attributes) {

        // 다른 소셜로 가입했다면, 다시 가입하지 못하게

//        User user = userRepository.findByUserEmail(attributes.getUserEmail())
//                .map(entity-> entity.update(
//                        attributes.getName(),
//                        attributes.getNickName(),
//                        attributes.getPicture()))
//                .orElse(attributes.toEntity());

        User user = userRepository.findByUserEmail(attributes.getUserEmail())
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
