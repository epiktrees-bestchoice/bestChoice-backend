package bestChoicebackend.spring.controller

import bestChoicebackend.spring.config.auth.dto.SessionUser
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    private val httpSession: HttpSession
) {
    @GetMapping("/")
    fun home(model: Model): String{
        model.addAttribute("hi", "hi")
        val user = httpSession.getAttribute("user") as SessionUser?

        if (user != null) {
            model.addAttribute("user",user)
        }
        return "home.html"
    }

    @GetMapping("/login")
    fun login(model: Model):String {
        return "login"
    }
}