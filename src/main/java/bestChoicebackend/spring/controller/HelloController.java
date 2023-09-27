package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.domain.Accommodation;
import com.nimbusds.jose.Header;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] myCookies = request.getCookies();

        if (myCookies != null) {
            for (int i = 0; i < myCookies.length; i++) {
                System.out.println(i + "번째 쿠키 이름: " + myCookies[i].getName());
                System.out.println(i + "번째 쿠키 값: " + myCookies[i].getValue());

                if ("JSESSIONID".equals(myCookies[i].getName())) {
                    // JSESSIONID 쿠키를 새로운 쿠키로 설정
                    Cookie myCookie = new Cookie(myCookies[i].getName(), myCookies[i].getValue());
                    myCookie.setMaxAge(3600);
                    myCookie.setDomain("epicktrees.net");
                    myCookie.setPath("/"); // 모든 경로에서 접근 가능하도록 설정
                    response.addCookie(myCookie);
                }
            }
        }

        // https://epicktrees.net으로 리다이렉트
        return "redirect:https://epicktrees.net";
    }


}
