package bestChoicebackend.spring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    fun home(model: Model): String{
        return "home"
    }

    @GetMapping("/error")
    fun error(model: Model): String {
        model.addAttribute("error")
        return "error"
    }
}