package bestChoicebackend.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/reserve")
    @ResponseBody
    public String reserveList(Authentication authentication){
        return "user reserve list";
    }

}
