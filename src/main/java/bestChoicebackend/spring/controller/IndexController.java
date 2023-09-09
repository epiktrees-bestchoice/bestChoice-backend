package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/oauth")
public class IndexController {

    @GetMapping("/loginInfo")
    @ResponseBody
    public String oauthLoginInfo(Authentication authentication){
        //oAuth2User.toString() 예시 : Name: [2346930276], Granted Authorities: [[USER]], User Attributes: [{id=2346930276, provider=kakao, name=김준우, email=bababoll@naver.com}]
        // SecurityContextHolder가 Bean에 저장됨 controller가 가져옴
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        //attributes.toString() 예시 : {id=2346930276, provider=kakao, name=김준우, email=bababoll@naver.com}
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return attributes.toString();
    }

    @GetMapping("/")
    @ResponseBody
    public String home(){
        return "wellcome root page";
    }

    @GetMapping("/userpage")
    public String userInfo(Authentication authentication, Model model){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        model.addAttribute("data",attributes.get("name"));
        return "hello";
    }
}
