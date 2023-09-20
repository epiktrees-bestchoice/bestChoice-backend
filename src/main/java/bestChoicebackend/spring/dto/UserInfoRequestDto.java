package bestChoicebackend.spring.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Controller
public class UserInfoRequestDto {

    String name;
    String nickName;
    String phoneNumber;
    String picture;

}
