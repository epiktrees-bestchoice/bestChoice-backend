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

        System.out.println("OAuth2User");
        System.out.println(oAuth2User);

        //현재 로그인 중인 서비스
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("--registrationId--");
        System.out.println(registrationId);

        //Primary key
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        System.out.println("--userName--");
        System.out.println(userNameAttributeName);

        //OAuth2UserService 를 이용해 가져온 OAuth2User 의 attribute
        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        System.out.println("--attributes--");
        System.out.println(attributes);

        User user = saveOrUpdate(attributes);
        System.out.println("--user--");
        System.out.println(user);

        //사용자 정보 저장
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByUserEmail(attributes.getUserEmail())
                .map(entity-> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}