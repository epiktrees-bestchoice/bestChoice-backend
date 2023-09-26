package bestChoicebackend.spring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000 , https://epicktrees.net")
@RestController
public class CookieController {

    @GetMapping("/account")
    public String getAccount(HttpServletRequest request){
        Cookie[] list = request.getCookies();
        for(Cookie cookie:list) {
            if(cookie.getName().equals("JSESSIONID")) {
                System.out.println(cookie.getValue());
            }
        }
        return "cookie getting!!";
    }


}
