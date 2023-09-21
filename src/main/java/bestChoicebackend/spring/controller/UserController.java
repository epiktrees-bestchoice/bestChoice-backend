package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        // spring config에 저장된 userService 빈을 의존성 주입한다.
        this.userService = userService;
    }


}
