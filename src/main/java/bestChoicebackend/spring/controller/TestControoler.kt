package bestChoicebackend.spring.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping("/ktspoint")
    fun foo(@AuthenticationPrincipal oauth2User: OAuth2User, model: Model) : String{
        val attributes = oauth2User.attributes
        model.addAttribute("data", attributes["name"])
        return "hello"
    }
}