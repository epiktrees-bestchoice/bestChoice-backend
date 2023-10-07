package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.config.auth.dto.SessionUser;
import bestChoicebackend.spring.domain.Accommodation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {

        System.out.println("root page test!!");
        return "welcome! root page"; // home.html 이 노출
    }

    @GetMapping("/try")
    @ResponseBody
    public String getTry(){
        return "try sucess";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<Boolean> getIsLogin(){
        System.out.println("Redirect login page");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "https://epicktrees.net/user");
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

//    @GetMapping("/login")
//    public String getIsLogin(){
//
//        return "login";
//    }

    @PostMapping("/my/logout")
    public String performLogout() {
        return "redirect:http://epicktrees.net";
    }
    @GetMapping("hello-world")
    public String  getIsLogin(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if(sessionUser != null){
            System.out.println(sessionUser.getEmail());
        }
        else{
            System.out.println("로그인 못했습니다.");
        }
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
        return "redirect:http://epicktrees.net";
    }

}
