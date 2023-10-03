package bestChoicebackend.spring.controller;

import bestChoicebackend.spring.config.auth.dto.SessionUser;
import bestChoicebackend.spring.domain.User;
import bestChoicebackend.spring.dto.UserInfoRequestDto;
import bestChoicebackend.spring.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/my")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        // spring config에 저장된 userService 빈을 의존성 주입한다.
        this.userService = userService;
    }

    @GetMapping("/info")
    @ResponseBody
    public Optional<User> getUserInfo(HttpSession httpSession){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        return userService.findByUserEmail(sessionUser.getEmail());
    }

    @GetMapping("/manage")
    public String putForm() {
        return "putUserForm";
    }

    @PostMapping("/info")
    @ResponseBody
    public User putUserInfo(HttpSession httpSession, UserInfoRequestDto userInfoRequestDto){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User user = userService.findByUserEmail(sessionUser.getEmail())
                .orElseThrow();

        userService.update(user,
                userInfoRequestDto.getName(),
                userInfoRequestDto.getNickName(),
                userInfoRequestDto.getPhoneNumber(),
                userInfoRequestDto.getPicture());

        return user;
    }

    @PostMapping("/logout")
    public void  userLogout(HttpServletResponse response, HttpSession httpSession){
        try {
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            System.out.println(sessionUser.getName() + "을 제거합니다.");
            httpSession.removeAttribute("user");

//            Cookie myCookie = new Cookie("JSESSIONID", null);
//            myCookie.setMaxAge(0);
//            myCookie.setPath("/");
//            myCookie.setDomain("");
//            response.addCookie(myCookie);

            // 로그아웃 성공 시 HTTP 상태 코드 200(OK)를 반환
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            // 로그아웃 실패 시 HTTP 상태 코드 500(Internal Server Error)를 반환
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
