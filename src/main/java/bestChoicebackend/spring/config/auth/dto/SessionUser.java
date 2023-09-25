package bestChoicebackend.spring.config.auth.dto;

import bestChoicebackend.spring.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // Serializable을 통해 직렬화
    // 직렬화된 엔티티 클래스를 다른 엔티티 클래스가 연관되면 다른 엔티티도 직렬화가 된다. -> 성능 이슈, 부수 효과 발생!!
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getUserEmail();
        this.picture = user.getPicture();
    }
}
