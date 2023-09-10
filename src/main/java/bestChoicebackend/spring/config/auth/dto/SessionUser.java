package bestChoicebackend.spring.config.auth.dto;

import bestChoicebackend.spring.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getUserEmail();
        this.picture = user.getPicture();
    }
}
