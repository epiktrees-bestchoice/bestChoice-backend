package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.domain.Accommodation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
